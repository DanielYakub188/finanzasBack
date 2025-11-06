package finanzas_pro.services;

import finanzas_pro.models.entities.DatosCompañia;
import finanzas_pro.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class MercadosService {


    @Autowired
    private DatosCompaniaRepository datosCompaniaRepository;

    @Autowired
    private DatosEmpresaRepository datosEmpresaRepository;

    @Autowired
    private PrecioYDatosGeneralesRepository precioYDatosGeneralesRepository;

    @Autowired
    private RatiosDeValoracionRepository ratiosDeValoracionRepository;

    @Autowired
    private RatiosRentabilidadRepository ratiosRentabilidadRepository;

    @Autowired
    private PosicionFinancieraRepository posicionFinancieraRepository;

    @Autowired
    private MargenesDeLaCompaniaRepository margenesDeLaCompaniaRepository;

    @Autowired
    private DividendosRepository dividendosRepository;

    public List<Map<String, Object>> recogerListaEmpresasPorSector(String sector, int pagina) {
        var empresas = datosCompaniaRepository.findBySector(sector, PageRequest.of(pagina, 10));

        List<Map<String, Object>> resultados = new ArrayList<>();

        //Por cada empresa y company symbol:
        empresas.forEach(datosCompania -> {
            String symbol = datosCompania.getCompanySymbol();
            Map<String, Object> fila = new LinkedHashMap<>();

            fila.put("companySymbol", symbol);

            var datosCompaniaPais = datosCompaniaRepository.findByCompanySymbol(symbol);
            if (datosCompaniaPais != null) {
                fila.put("pais", datosCompaniaPais.getPais());
            }

            var datosEmpresaBasicos = datosEmpresaRepository.findByCompanySymbol(symbol);
            if (datosEmpresaBasicos != null) {
                fila.put("companyName", datosEmpresaBasicos.getCompanyName());
            }

            var precioDatos = precioYDatosGeneralesRepository.findByCompanySymbol(symbol);
            if (precioDatos != null) {
                fila.put("perTtm", precioDatos.getPerTtm());
                fila.put("situacionCaja", precioDatos.getSituacionCaja());
            }

            var ratioValoracion = ratiosDeValoracionRepository.findByCompanySymbol(symbol);
            if (ratioValoracion != null) {
                fila.put("evFcf", ratioValoracion.getEvFcf());
            }

            var ratioRentabilidad = ratiosRentabilidadRepository.findByCompanySymbol(symbol);
            if (ratioRentabilidad != null) {
                fila.put("roce", ratioRentabilidad.getRoce());
            }

            var posicionFinanciera = posicionFinancieraRepository.findByCompanySymbol(symbol);
            if (posicionFinanciera != null) {
                fila.put("ratioDeuda", posicionFinanciera.getRatioDeuda());
                fila.put("liquidez", posicionFinanciera.getLiquidez());
            }

            var margenesSobreVentas = margenesDeLaCompaniaRepository.findByCompanySymbol(symbol);
            if (margenesSobreVentas != null) {
                fila.put("margenBruto", margenesSobreVentas.getMargenBruto());
                fila.put("margenNeto", margenesSobreVentas.getMargenNeto());
            }

            var dividendos = dividendosRepository.findByCompanySymbol(symbol);
            if (dividendos != null) {
                fila.put("dividendYieldTtm", dividendos.getDividendYieldTtm());
                fila.put("payoutRatioTtm", dividendos.getPayoutRatioTtm());
            }
            resultados.add(fila);
        });
        return resultados;
    }

    public List<Map<String, Object>> recogerListaEmpresasPorPais(String pais, int pagina) {
        var empresas = datosCompaniaRepository.findByPais(pais, PageRequest.of(pagina, 10));

        List<Map<String, Object>> resultados = new ArrayList<>();

        //Por cada empresa y company symbol:
        empresas.forEach(datosCompania -> {
            String symbol = datosCompania.getCompanySymbol();
            Map<String, Object> fila = new LinkedHashMap<>();

            fila.put("companySymbol", symbol);

            var datosCompaniaPais = datosCompaniaRepository.findByCompanySymbol(symbol);
            if (datosCompaniaPais != null) {
                fila.put("pais", datosCompaniaPais.getPais());
            }

            var datosEmpresaBasicos = datosEmpresaRepository.findByCompanySymbol(symbol);
            if (datosEmpresaBasicos != null) {
                fila.put("companyName", datosEmpresaBasicos.getCompanyName());
            }

            var precioDatos = precioYDatosGeneralesRepository.findByCompanySymbol(symbol);
            if (precioDatos != null) {
                fila.put("perTtm", precioDatos.getPerTtm());
                fila.put("situacionCaja", precioDatos.getSituacionCaja());
            }

            var ratioValoracion = ratiosDeValoracionRepository.findByCompanySymbol(symbol);
            if (ratioValoracion != null) {
                fila.put("evFcf", ratioValoracion.getEvFcf());
            }

            var ratioRentabilidad = ratiosRentabilidadRepository.findByCompanySymbol(symbol);
            if (ratioRentabilidad != null) {
                fila.put("roce", ratioRentabilidad.getRoce());
            }

            var posicionFinanciera = posicionFinancieraRepository.findByCompanySymbol(symbol);
            if (posicionFinanciera != null) {
                fila.put("ratioDeuda", posicionFinanciera.getRatioDeuda());
                fila.put("liquidez", posicionFinanciera.getLiquidez());
            }

            var margenesSobreVentas = margenesDeLaCompaniaRepository.findByCompanySymbol(symbol);
            if (margenesSobreVentas != null) {
                fila.put("margenBruto", margenesSobreVentas.getMargenBruto());
                fila.put("margenNeto", margenesSobreVentas.getMargenNeto());
            }

            var dividendos = dividendosRepository.findByCompanySymbol(symbol);
            if (dividendos != null) {
                fila.put("dividendYieldTtm", dividendos.getDividendYieldTtm());
                fila.put("payoutRatioTtm", dividendos.getPayoutRatioTtm());
            }
            resultados.add(fila);
        });
        return resultados;
    }

    public List<Map<String, Object>> recogerListaEmpresasPorBolsa(String bolsa, int pagina) {
        var empresas = datosCompaniaRepository.findByBolsa(bolsa, PageRequest.of(pagina, 10));

        List<Map<String, Object>> resultados = new ArrayList<>();

        //Por cada empresa y company symbol:
        empresas.forEach(datosCompania -> {
            String symbol = datosCompania.getCompanySymbol();
            Map<String, Object> fila = new LinkedHashMap<>();

            fila.put("companySymbol", symbol);

            var datosCompaniaPais = datosCompaniaRepository.findByCompanySymbol(symbol);
            if (datosCompaniaPais != null) {
                fila.put("pais", datosCompaniaPais.getPais());
            }

            var datosEmpresaBasicos = datosEmpresaRepository.findByCompanySymbol(symbol);
            if (datosEmpresaBasicos != null) {
                fila.put("companyName", datosEmpresaBasicos.getCompanyName());
            }

            var precioDatos = precioYDatosGeneralesRepository.findByCompanySymbol(symbol);
            if (precioDatos != null) {
                fila.put("perTtm", precioDatos.getPerTtm());
                fila.put("situacionCaja", precioDatos.getSituacionCaja());
            }

            var ratioValoracion = ratiosDeValoracionRepository.findByCompanySymbol(symbol);
            if (ratioValoracion != null) {
                fila.put("evFcf", ratioValoracion.getEvFcf());
            }

            var ratioRentabilidad = ratiosRentabilidadRepository.findByCompanySymbol(symbol);
            if (ratioRentabilidad != null) {
                fila.put("roce", ratioRentabilidad.getRoce());
            }

            var posicionFinanciera = posicionFinancieraRepository.findByCompanySymbol(symbol);
            if (posicionFinanciera != null) {
                fila.put("ratioDeuda", posicionFinanciera.getRatioDeuda());
                fila.put("liquidez", posicionFinanciera.getLiquidez());
            }

            var margenesSobreVentas = margenesDeLaCompaniaRepository.findByCompanySymbol(symbol);
            if (margenesSobreVentas != null) {
                fila.put("margenBruto", margenesSobreVentas.getMargenBruto());
                fila.put("margenNeto", margenesSobreVentas.getMargenNeto());
            }

            var dividendos = dividendosRepository.findByCompanySymbol(symbol);
            if (dividendos != null) {
                fila.put("dividendYieldTtm", dividendos.getDividendYieldTtm());
                fila.put("payoutRatioTtm", dividendos.getPayoutRatioTtm());
            }
            resultados.add(fila);
        });
        return resultados;
    }

    public List<Map<String, Object>> recogerListaEmpresasPorIndustria(String industria, int pagina) {
        var empresas = datosCompaniaRepository.findByIndustria(industria, PageRequest.of(pagina, 10));

        List<Map<String, Object>> resultados = new ArrayList<>();

        //Por cada empresa y company symbol:
        empresas.forEach(datosCompania -> {
            String symbol = datosCompania.getCompanySymbol();
            Map<String, Object> fila = new LinkedHashMap<>();

            fila.put("companySymbol", symbol);

            var datosCompaniaPais = datosCompaniaRepository.findByCompanySymbol(symbol);
            if (datosCompaniaPais != null) {
                fila.put("pais", datosCompaniaPais.getPais());
            }

            var datosEmpresaBasicos = datosEmpresaRepository.findByCompanySymbol(symbol);
            if (datosEmpresaBasicos != null) {
                fila.put("companyName", datosEmpresaBasicos.getCompanyName());
            }

            var precioDatos = precioYDatosGeneralesRepository.findByCompanySymbol(symbol);
            if (precioDatos != null) {
                fila.put("perTtm", precioDatos.getPerTtm());
                fila.put("situacionCaja", precioDatos.getSituacionCaja());
            }

            var ratioValoracion = ratiosDeValoracionRepository.findByCompanySymbol(symbol);
            if (ratioValoracion != null) {
                fila.put("evFcf", ratioValoracion.getEvFcf());
            }

            var ratioRentabilidad = ratiosRentabilidadRepository.findByCompanySymbol(symbol);
            if (ratioRentabilidad != null) {
                fila.put("roce", ratioRentabilidad.getRoce());
            }

            var posicionFinanciera = posicionFinancieraRepository.findByCompanySymbol(symbol);
            if (posicionFinanciera != null) {
                fila.put("ratioDeuda", posicionFinanciera.getRatioDeuda());
                fila.put("liquidez", posicionFinanciera.getLiquidez());
            }

            var margenesSobreVentas = margenesDeLaCompaniaRepository.findByCompanySymbol(symbol);
            if (margenesSobreVentas != null) {
                fila.put("margenBruto", margenesSobreVentas.getMargenBruto());
                fila.put("margenNeto", margenesSobreVentas.getMargenNeto());
            }

            var dividendos = dividendosRepository.findByCompanySymbol(symbol);
            if (dividendos != null) {
                fila.put("dividendYieldTtm", dividendos.getDividendYieldTtm());
                fila.put("payoutRatioTtm", dividendos.getPayoutRatioTtm());
            }
            resultados.add(fila);
        });
        return resultados;
    }

    public List<Map<String, Object>> consultarEmpresasPaisSector(String pais, String sector, int pagina) {

        Page<DatosCompañia> empresas;

        if (pais != null && !pais.isEmpty() && sector != null && !sector.isEmpty()) {
            // Ambos filtros
            empresas = datosCompaniaRepository.findByPaisAndSector(pais, sector, PageRequest.of(pagina, 10));
        } else if (pais != null && !pais.isEmpty()) {
            // Solo país
            empresas = datosCompaniaRepository.findByPais(pais, PageRequest.of(pagina, 10));
        } else if (sector != null && !sector.isEmpty()) {
            // Solo sector
            empresas = datosCompaniaRepository.findBySector(sector, PageRequest.of(pagina, 10));
        } else {
            // Ninguno: devolver todas
            empresas = datosCompaniaRepository.findAll(PageRequest.of(pagina, 10));
        }

        List<Map<String, Object>> resultados = new ArrayList<>();

        empresas.forEach(datosCompania -> {
            String symbol = datosCompania.getCompanySymbol();
            Map<String, Object> fila = new LinkedHashMap<>();

            fila.put("companySymbol", symbol);
            fila.put("sector", datosCompania.getSector());
            var datosCompaniaPais = datosCompaniaRepository.findByCompanySymbol(symbol);
            if (datosCompaniaPais != null) {
                fila.put("pais", datosCompaniaPais.getPais());
            }

            var datosEmpresaBasicos = datosEmpresaRepository.findByCompanySymbol(symbol);
            if (datosEmpresaBasicos != null) {
                fila.put("companyName", datosEmpresaBasicos.getCompanyName());
            }

            var precioDatos = precioYDatosGeneralesRepository.findByCompanySymbol(symbol);
            if (precioDatos != null) {
                fila.put("perTtm", precioDatos.getPerTtm());
                fila.put("situacionCaja", precioDatos.getSituacionCaja());
            }

            var ratioValoracion = ratiosDeValoracionRepository.findByCompanySymbol(symbol);
            if (ratioValoracion != null) {
                fila.put("evFcf", ratioValoracion.getEvFcf());
            }

            var ratioRentabilidad = ratiosRentabilidadRepository.findByCompanySymbol(symbol);
            if (ratioRentabilidad != null) {
                fila.put("roce", ratioRentabilidad.getRoce());
            }

            var posicionFinanciera = posicionFinancieraRepository.findByCompanySymbol(symbol);
            if (posicionFinanciera != null) {
                fila.put("ratioDeuda", posicionFinanciera.getRatioDeuda());
                fila.put("liquidez", posicionFinanciera.getLiquidez());
            }

            var margenesSobreVentas = margenesDeLaCompaniaRepository.findByCompanySymbol(symbol);
            if (margenesSobreVentas != null) {
                fila.put("margenBruto", margenesSobreVentas.getMargenBruto());
                fila.put("margenNeto", margenesSobreVentas.getMargenNeto());
            }

            var dividendos = dividendosRepository.findByCompanySymbol(symbol);
            if (dividendos != null) {
                fila.put("dividendYieldTtm", dividendos.getDividendYieldTtm());
                fila.put("payoutRatioTtm", dividendos.getPayoutRatioTtm());
            }

            resultados.add(fila);
        });

        return resultados;
    }

    public long consultarNumeroDeResultados(String pais, String sector, String bolsa, String industria) {
        int resultados = 0;

        if (pais != null && !pais.isEmpty() && sector != null && !sector.isEmpty()) {
            // Ambos filtros

        } else if (pais != null && !pais.isEmpty()) {
            // Solo país
            resultados = this.datosCompaniaRepository.countByPais(pais);
        } else if (sector != null && !sector.isEmpty()) {
            // Solo sector
            resultados = this.datosCompaniaRepository.countBySector(sector);
        } else if (bolsa != null && !bolsa.isEmpty()) {
            //Solo bolsa
            resultados = this.datosCompaniaRepository.countByBolsa(bolsa);
        } else if (industria != null && !industria.isEmpty()) {
            resultados = this.datosCompaniaRepository.countByIndustria(industria);
        } else {
        }

        return resultados;
    }
}
