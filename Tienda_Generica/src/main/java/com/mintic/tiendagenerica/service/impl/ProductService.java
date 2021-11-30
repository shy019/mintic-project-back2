package com.mintic.tiendagenerica.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mintic.tiendagenerica.dto.request.ProductRequestDTO;
import com.mintic.tiendagenerica.dto.response.ProductResponseDTO;
import com.mintic.tiendagenerica.dto.response.SupplierResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;
import com.mintic.tiendagenerica.model.Product;
import com.mintic.tiendagenerica.model.Supplier;
import com.mintic.tiendagenerica.repository.IProductRepository;
import com.mintic.tiendagenerica.service.IProductService;

@Service
public class ProductService implements IProductService {

	private IProductRepository iProductRepository;
	private ModelMapper modelMapper;

	public ProductService(IProductRepository iProductRepository, ModelMapper modelMapper) {
		this.iProductRepository = iProductRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<ProductResponseDTO> getProducts() throws TiendaGenericaException {
		List<ProductResponseDTO> productos = iProductRepository.findAll().stream()
				.map(user -> this.modelMapper.map(user, ProductResponseDTO.class)).collect(Collectors.toList());

		if (productos.isEmpty())
			throw new TiendaGenericaException("No hay productos en el sistema");
		else
			return productos;

	}

	@Override
	public ProductResponseDTO getProductByCodigoProducto(Long codigoProducto) throws TiendaGenericaException {
		return this.modelMapper.map(
				iProductRepository.findProductByCodigoProducto(codigoProducto)
						.orElseThrow(() -> new TiendaGenericaException("No hay un producto con ese código")),
				ProductResponseDTO.class);

	}

	@Override
	public ProductResponseDTO saveProduct(ProductRequestDTO producto) throws TiendaGenericaException {

		iProductRepository.findProductByCodigoProducto(producto.getCodigoProducto())
				.ifPresent(productoDuplicado -> new TiendaGenericaException(
						"El producto ya existe en la base de datos"));

		return this.modelMapper.map(iProductRepository.save(this.modelMapper.map(producto, Product.class)),
				ProductResponseDTO.class);
	}

	@Override
	public ProductResponseDTO deleteProduct(Long codigoProducto) throws TiendaGenericaException {

		Product producto = iProductRepository.findProductByCodigoProducto(codigoProducto)
				.orElseThrow(() -> new TiendaGenericaException("No hay un producto con este código"));

		iProductRepository.deleteByCodigoProducto(codigoProducto);
		return this.modelMapper.map(producto, ProductResponseDTO.class);

	}

	@Override
	public ProductResponseDTO updateProduct(ProductRequestDTO product) throws TiendaGenericaException {

		Optional<Product> nuevoProducto = iProductRepository.findProductByCodigoProducto(product.getCodigoProducto());

		if (nuevoProducto.isPresent())
			nuevoProducto.map(producto -> {
				producto.setNombreProducto(product.getNombreProducto());
				producto.setNitProveedor(this.modelMapper.map(product.getNitProveedor(), Supplier.class));
				producto.setPrecioCompra(product.getPrecioCompra());
				producto.setPrecioVenta(product.getPrecioVenta());
				return producto;
			}).get();
		else
			throw new TiendaGenericaException(
					"No existe un producto con ese código: ".concat(product.getCodigoProducto() + ""));

		Product productoGuardado = iProductRepository.save(nuevoProducto.get());
		ProductResponseDTO productoRespuesta = new ProductResponseDTO();
		productoRespuesta.setCodigoProducto(productoGuardado.getCodigoProducto());
		productoRespuesta.setIvaCompra(productoGuardado.getIvaCompra());
		productoRespuesta
				.setNitProveedor(this.modelMapper.map(productoGuardado.getNitProveedor(), SupplierResponseDTO.class));
		productoRespuesta.setNombreProducto(productoGuardado.getNombreProducto());
		productoRespuesta.setPrecioCompra(productoGuardado.getPrecioCompra());
		productoRespuesta.setPrecioVenta(productoGuardado.getPrecioVenta());

		return productoRespuesta;
	}

	@Override
	public ProductResponseDTO getProductByName(String nombreProducto) throws TiendaGenericaException {

		return this.modelMapper.map(
				iProductRepository.findProductByNombreProducto(nombreProducto)
						.orElseThrow(() -> new TiendaGenericaException("No hay un producto con ese nombre")),
				ProductResponseDTO.class);

	}

}
