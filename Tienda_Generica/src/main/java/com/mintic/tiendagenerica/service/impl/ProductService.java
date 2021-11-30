package com.mintic.tiendagenerica.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mintic.tiendagenerica.dto.request.ProductAllRequestDTO;
import com.mintic.tiendagenerica.dto.request.ProductRequestDTO;
import com.mintic.tiendagenerica.dto.response.ProductResponseDTO;
import com.mintic.tiendagenerica.dto.response.ResponseDTO;
import com.mintic.tiendagenerica.dto.response.SupplierResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;
import com.mintic.tiendagenerica.model.Product;
import com.mintic.tiendagenerica.model.Supplier;
import com.mintic.tiendagenerica.repository.IProductRepository;
import com.mintic.tiendagenerica.repository.ISupplierRepository;
import com.mintic.tiendagenerica.service.IProductService;

@Service
public class ProductService implements IProductService {

	private IProductRepository iProductRepository;
	private ISupplierRepository iSupplierRepository;
	private ModelMapper modelMapper;

	public ProductService(IProductRepository iProductRepository, ISupplierRepository iSupplierRepository,
			ModelMapper modelMapper) {
		super();
		this.iProductRepository = iProductRepository;
		this.iSupplierRepository = iSupplierRepository;
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
		if (iProductRepository.findProductByCodigoProducto(producto.getCodigoProducto()).isPresent())
			throw new TiendaGenericaException(
					"Ya existe un producto con ese código: ".concat(producto.getCodigoProducto() + ""));

		if (!iSupplierRepository.findSupplierByNitProveedor(producto.getNitProveedor().getNitProveedor()).isPresent())
			throw new TiendaGenericaException("El proveedor ingresado no existe en la base de datos: "
					.concat(producto.getNitProveedor().getNombreProveedor() + ""));

		Product productoGuardado = iProductRepository.save(this.modelMapper.map(producto, Product.class));
		return this.modelMapper.map(productoGuardado, ProductResponseDTO.class);
	}

	@Override
	public ProductResponseDTO deleteProduct(Long codigoProducto) throws TiendaGenericaException {

		Product producto = iProductRepository.findProductByCodigoProducto(codigoProducto)
				.orElseThrow(() -> new TiendaGenericaException("No hay un producto con este código"));

		iProductRepository.deleteByCodigoProducto(codigoProducto);
		return this.modelMapper.map(producto, ProductResponseDTO.class);

	}

	@Override
	public ProductResponseDTO updateProduct(ProductRequestDTO producto) throws TiendaGenericaException {

		Optional<Product> nuevoProducto = iProductRepository.findProductByCodigoProducto(producto.getCodigoProducto());

		if (nuevoProducto.isPresent())
			nuevoProducto.map(nuevoProducto2 -> {
				nuevoProducto2.setNombreProducto(producto.getNombreProducto());
				nuevoProducto2.setNitProveedor(this.modelMapper.map(producto.getNitProveedor(), Supplier.class));
				nuevoProducto2.setPrecioCompra(producto.getPrecioCompra());
				nuevoProducto2.setPrecioVenta(producto.getPrecioVenta());
				return nuevoProducto2;
			}).get();
		else
			throw new TiendaGenericaException(
					"No existe un producto con ese código: ".concat(producto.getCodigoProducto() + ""));

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

	@Override
	public ResponseDTO saveAllProduct(ProductAllRequestDTO[] productos) throws TiendaGenericaException {

		int contador = 0;
		List<ProductResponseDTO> guardados = new ArrayList<ProductResponseDTO>();
		List<ProductResponseDTO> noGuardados = new ArrayList<ProductResponseDTO>();

		for (ProductAllRequestDTO producto : productos) {
			if (iProductRepository.findProductByCodigoProducto(producto.getCodigoProducto()).isPresent()) {
				noGuardados.add(this.modelMapper.map(producto, ProductResponseDTO.class));
				continue;
			}

			Optional<Supplier> supplier = iSupplierRepository.findSupplierByNitProveedor(producto.getNitProveedor());
			if (!supplier.isPresent()) {
				noGuardados.add(this.modelMapper.map(producto, ProductResponseDTO.class));
				continue;
			}

			Product nuevoProducto = new Product();
			nuevoProducto.setCodigoProducto(producto.getCodigoProducto());
			nuevoProducto.setIvaCompra(producto.getIvaCompra());
			nuevoProducto.setNitProveedor(supplier.get());
			nuevoProducto.setNombreProducto(producto.getNombreProducto());
			nuevoProducto.setPrecioCompra(producto.getPrecioCompra());
			nuevoProducto.setPrecioVenta(producto.getPrecioVenta());

			Product productoGuardado = iProductRepository.save(nuevoProducto);
			guardados.add(this.modelMapper.map(productoGuardado, ProductResponseDTO.class));
			contador++;
		}

		return new ResponseDTO(contador, "Se guardaron ".concat(contador + "").concat(" registros y no se guardaron ")
				.concat((productos.length - contador) + "").concat(" registros."), guardados, noGuardados);
	}

}
