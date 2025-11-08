package finanzas_pro.controllers;

import finanzas_pro.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;


    @GetMapping("/extendida")
    public List<Map<String, Object>> consultarPaisSectorIndustria(
            @RequestHeader(value = "paises", required = false) List<String> paises,
            @RequestHeader(value = "sectores", required = false) List<String> sectores,
            @RequestHeader(value = "industrias", required = false) List<String> industrias,
            @RequestHeader(value = "page", defaultValue = "0") int page,
            @RequestHeader(value = "ordenarPor", required = false) String ordenarPor,
            @RequestHeader(value = "direccionOrden", required = false) String direccionOrden
    ) {
        List<Map<String, Object>> resultados = consultaService.consultarEmpresasPaisSectorIndustria(paises, sectores, industrias, page, ordenarPor, direccionOrden);
        return resultados;
    }

    @GetMapping("/count")
    public long contarEmpresas(
            @RequestHeader(value = "paises", required = false) List<String> paises,
            @RequestHeader(value = "sectores", required = false) List<String> sectores,
            @RequestHeader(value = "industrias", required = false) List<String> industrias) {
        return consultaService.consultarNumeroDeResultados(paises, sectores, industrias);
    }

    //A
    @GetMapping("/lista-datos")
    public Map<String, List<String>> recogerDatos() {
        Map<String, List<String>> listaDatos = consultaService.recogerPaisesIndustriasYSectoresDisponibles();
        return listaDatos;
    }

}
