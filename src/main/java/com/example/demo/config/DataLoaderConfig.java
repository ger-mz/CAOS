package com.example.demo.config;

import com.example.demo.entity.EstadoSolicitud;
import com.example.demo.entity.Producto;
import com.example.demo.entity.Proveedor;
import com.example.demo.entity.enums.MonedaProdutoEnum;
import com.example.demo.entity.enums.StatusStateEnum;
import com.example.demo.repository.MaquinaEstadosRepository;
import com.example.demo.repository.ProveedorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class DataLoaderConfig {
    @Bean
    CommandLineRunner initDatabase(ProveedorRepository proveedorRepository,
            MaquinaEstadosRepository solicitudRepository) {
        return args -> {

            // 1. Verificar si ya existen datos para no duplicar al reiniciar
            if (proveedorRepository.count() == 0) {

                System.out.println("Cargando datos de prueba...");

                // --- CREAR PROVEEDOR Y PRODUCTOS ---
                Proveedor proveedor = new Proveedor();
                proveedor.setRazonSocial("Tech Solutions S.A. de C.V.");
                proveedor.setRfc("TESO990101QAZ");
                proveedor.setCodigoPostal("06600");
                proveedor.setRegimenFiscal("601");
                proveedor.setEmail("example@gmail.com");
                proveedor.setCelular("5512345678");

                // Crear productos
                Producto p1 = new Producto();
                p1.setNombre("Laptop Gamer");
                p1.setDescripcion("i7, 16GB RAM");
                p1.setPrecio(25000L); // Nota la L para Long
                p1.setMoneda(MonedaProdutoEnum.MXN); // Ajusta a tu Enum real

                Producto p2 = new Producto();
                p2.setNombre("Mouse Inalámbrico");
                p2.setDescripcion("Ergonómico");
                p2.setPrecio(500L);
                p2.setMoneda(MonedaProdutoEnum.MXN);

                // Asignar productos al proveedor
                List<Producto> catalogo = new ArrayList<>();
                catalogo.add(p1);
                catalogo.add(p2);
                proveedor.setCatalogo(catalogo);

                // GUARDAR PROVEEDOR (El CascadeType.ALL guardará automáticamente los productos)
                Proveedor proveedorGuardado = proveedorRepository.save(proveedor);

                EstadoSolicitud solicitud = new EstadoSolicitud();
                solicitud.setState(StatusStateEnum.CREADO); // Ajusta a tu Enum real
                solicitud.setFechaSolicitud(new Date());
                solicitud.setNumeroUnidades(10L);

                // Relaciones
                solicitud.setProveedor(proveedorGuardado);
                // Tomamos el primer producto guardado del catálogo (que ya tiene ID)
                solicitud.setProductoSolicitado(proveedorGuardado.getCatalogo().get(0));
                solicitud.setPaqueteRecibido(false);

                // Guardar Solicitud
                solicitudRepository.save(solicitud);

                EstadoSolicitud solicitud2 = new EstadoSolicitud();
                solicitud2.setState(StatusStateEnum.CREADO); // Ajusta a tu Enum real
                solicitud2.setFechaSolicitud(new Date());
                solicitud2.setNumeroUnidades(5L);

                // Relaciones
                solicitud2.setProveedor(proveedorGuardado);
                // Tomamos el primer producto guardado del catálogo (que ya tiene ID)
                solicitud2.setProductoSolicitado(proveedorGuardado.getCatalogo().get(1));
                solicitud2.setPaqueteRecibido(false);

                solicitudRepository.save(solicitud2);

                EstadoSolicitud solicitud3 = new EstadoSolicitud();
                solicitud3.setState(StatusStateEnum.CREADO);
                solicitud3.setFechaSolicitud(new Date());
                solicitud3.setNumeroUnidades(2L);
                solicitud3.setProveedor(proveedorGuardado);
                solicitud3.setProductoSolicitado(proveedorGuardado.getCatalogo().get(0));
                solicitud3.setPaqueteRecibido(false);
                solicitudRepository.save(solicitud3);

                EstadoSolicitud solicitud4 = new EstadoSolicitud();
                solicitud4.setState(StatusStateEnum.CREADO);
                solicitud4.setFechaSolicitud(new Date());
                solicitud4.setNumeroUnidades(20L);
                solicitud4.setProveedor(proveedorGuardado);
                solicitud4.setProductoSolicitado(proveedorGuardado.getCatalogo().get(1));
                solicitud4.setPaqueteRecibido(false);
                solicitudRepository.save(solicitud4);

                // --- CREAR SEGUNDO PROVEEDOR ---
                Proveedor proveedor2 = new Proveedor();
                proveedor2.setRazonSocial("Global Supplies International");
                proveedor2.setRfc("GSIN001231PZ1");
                proveedor2.setCodigoPostal("01000");
                proveedor2.setRegimenFiscal("601");
                proveedor2.setEmail("ventas@globalsupplies.com");
                proveedor2.setCelular("5587654321");

                Producto p3 = new Producto();
                p3.setNombre("Monitor 4K 27\"");
                p3.setDescripcion("Panel IPS, 144Hz");
                p3.setPrecio(8500L);
                p3.setMoneda(MonedaProdutoEnum.MXN);

                List<Producto> catalogo2 = new ArrayList<>();
                catalogo2.add(p3);
                proveedor2.setCatalogo(catalogo2);

                Proveedor proveedor2Guardado = proveedorRepository.save(proveedor2);

                // Nueva solicitud para el segundo proveedor
                EstadoSolicitud solicitud5 = new EstadoSolicitud();
                solicitud5.setState(StatusStateEnum.CREADO);
                solicitud5.setFechaSolicitud(new Date());
                solicitud5.setNumeroUnidades(3L);
                solicitud5.setProveedor(proveedor2Guardado);
                solicitud5.setProductoSolicitado(proveedor2Guardado.getCatalogo().get(0));
                solicitud5.setPaqueteRecibido(false);
                solicitudRepository.save(solicitud5);

                System.out.println("Datos precargados exitosamente.");
            } else {
                System.out.println("La base de datos ya contiene datos. Omitiendo carga.");
            }
        };
    }
}
