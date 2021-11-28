package com.mintic.tiendagenerica.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mintic.tiendagenerica.dto.request.ProductRequestDTO;
import com.mintic.tiendagenerica.dto.response.ProductResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;
import com.mintic.tiendagenerica.service.IProductService;

@RestController
@RequestMapping("/api/tiendagenerica/product")
@CrossOrigin(origins = "*", maxAge = 3600L)
public class ProductController {

	@Qualifier("ProductService")
	IProductService iProductService;

	public ProductController(IProductService iProductService) {
		this.iProductService = iProductService;
	}

	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { "application/JSON" })
	public ResponseEntity<List<ProductResponseDTO>> getProducts() throws TiendaGenericaException {
		return new ResponseEntity<List<ProductResponseDTO>>(iProductService.getProducts(), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/{nitProveedor}", method = RequestMethod.GET, produces = { "application/JSON" })
	public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable("codigoProducto") Long codigoProducto)
			throws TiendaGenericaException {
		return new ResponseEntity<ProductResponseDTO>(iProductService.getProductByCodigoProducto(codigoProducto),
				HttpStatus.OK);
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/JSON" }, produces = {
			"application/JSON" })
	public ResponseEntity<ProductResponseDTO> save(@Valid @RequestBody ProductRequestDTO product)
			throws TiendaGenericaException {

		return new ResponseEntity<ProductResponseDTO>(iProductService.saveProduct(product), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/", method = RequestMethod.PUT, produces = { "application/JSON" }, consumes = {
			"application/JSON" })
	public ResponseEntity<ProductResponseDTO> editar(@RequestBody ProductRequestDTO product)
			throws TiendaGenericaException {
		return new ResponseEntity<ProductResponseDTO>(iProductService.updateProduct(product), HttpStatus.OK);
		// return new
		// ResponseEntity<SupplierResponseDTO>(iSupplierService.updateSupplier(supplier),
		// HttpStatus.OK);

	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/{nitProveedor}", method = RequestMethod.DELETE, produces = { "application/JSON" })
	public ResponseEntity<ProductResponseDTO> delete(@PathVariable("codigoProducto") Long codigoProducto)
			throws TiendaGenericaException {
		return new ResponseEntity<ProductResponseDTO>(iProductService.deleteProduct(codigoProducto), HttpStatus.OK);
	}

}
