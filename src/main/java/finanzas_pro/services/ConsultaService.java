package finanzas_pro.services;

import finanzas_pro.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConsultaService {


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


    /**
     * Consulta empresas según pais, sector o industria
     */

    /*
    public List<Map<String, Object>> consultarEmpresasPaisSectorIndustria(
            List<String> paises,
            List<String> sectores,
            List<String> industrias,
            int pagina,
            String ordenarPor,
            String direccionOrden
    ) {
        // === CONFIGURACIÓN DE ORDEN ===
        Sort sort = Sort.unsorted();
        if (ordenarPor != null && !ordenarPor.isEmpty()) {
            sort = "desc".equalsIgnoreCase(direccionOrden)
                    ? Sort.by(ordenarPor).descending()
                    : Sort.by(ordenarPor).ascending();
        }

        PageRequest pageRequest = PageRequest.of(pagina, 10, sort);
        Page<DatosCompañia> empresas;

        // === FILTRO COMBINADO ===
        if (paises != null && !paises.isEmpty()
                && sectores != null && !sectores.isEmpty()
                && industrias != null && !industrias.isEmpty()) {

            empresas = datosCompaniaRepository.findByPaisInAndSectorInAndIndustriaIn(paises, sectores, industrias, pageRequest);

        } else if (paises != null && !paises.isEmpty()
                && sectores != null && !sectores.isEmpty()) {

            empresas = datosCompaniaRepository.findByPaisInAndSectorIn(paises, sectores, pageRequest);

        } else if (paises != null && !paises.isEmpty()
                && industrias != null && !industrias.isEmpty()) {

            empresas = datosCompaniaRepository.findByPaisInAndIndustriaIn(paises, industrias, pageRequest);

        } else if (sectores != null && !sectores.isEmpty()
                && industrias != null && !industrias.isEmpty()) {

            empresas = datosCompaniaRepository.findBySectorInAndIndustriaIn(sectores, industrias, pageRequest);

        } else if (paises != null && !paises.isEmpty()) {
            empresas = datosCompaniaRepository.findByPaisIn(paises, pageRequest);

        } else if (sectores != null && !sectores.isEmpty()) {
            empresas = datosCompaniaRepository.findBySectorIn(sectores, pageRequest);

        } else if (industrias != null && !industrias.isEmpty()) {
            empresas = datosCompaniaRepository.findByIndustriaIn(industrias, pageRequest);

        } else {
            empresas = datosCompaniaRepository.findAll(pageRequest);
        }

        // === MAPEO DE RESULTADOS ===
        List<Map<String, Object>> resultados = new ArrayList<>();

        empresas.forEach(datosCompania -> {
            String symbol = datosCompania.getCompanySymbol();
            Map<String, Object> fila = new LinkedHashMap<>();

            fila.put("companySymbol", symbol);
            fila.put("pais", datosCompania.getPais());
            fila.put("sector", datosCompania.getSector());
            fila.put("industria", datosCompania.getIndustria());

            var datosEmpresaBasicos = datosEmpresaRepository.findByCompanySymbol(symbol);
            if (datosEmpresaBasicos != null)
                fila.put("companyName", datosEmpresaBasicos.getCompanyName());

            var precioDatos = precioYDatosGeneralesRepository.findByCompanySymbol(symbol);
            if (precioDatos != null) {
                fila.put("perTtm", precioDatos.getPerTtm());
                fila.put("situacionCaja", precioDatos.getSituacionCaja());
            }

            var ratioValoracion = ratiosDeValoracionRepository.findByCompanySymbol(symbol);
            if (ratioValoracion != null)
                fila.put("evFcf", ratioValoracion.getEvFcf());

            var ratioRentabilidad = ratiosRentabilidadRepository.findByCompanySymbol(symbol);
            if (ratioRentabilidad != null)
                fila.put("roce", ratioRentabilidad.getRoce());

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
*/

    /**
     * Cuenta el número total de resultados según país(es) y/o sector(es)
     */
    public long consultarNumeroDeResultados(List<String> paises, List<String> sectores, List<String> industrias) {
        paises = (paises != null && !paises.isEmpty()) ? paises : null;
        sectores = (sectores != null && !sectores.isEmpty()) ? sectores : null;
        industrias = (industrias != null && !industrias.isEmpty()) ? industrias : null;

        if (paises == null && sectores == null && industrias == null)
            return datosCompaniaRepository.count();

        if (paises != null && sectores != null && industrias != null)
            return datosCompaniaRepository.countByPaisInAndSectorInAndIndustriaIn(paises, sectores, industrias);
        if (paises != null && sectores != null)
            return datosCompaniaRepository.countByPaisInAndSectorIn(paises, sectores);
        if (paises != null && industrias != null)
            return datosCompaniaRepository.countByPaisInAndIndustriaIn(paises, industrias);
        if (sectores != null && industrias != null)
            return datosCompaniaRepository.countBySectorInAndIndustriaIn(sectores, industrias);
        if (paises != null)
            return datosCompaniaRepository.countByPaisIn(paises);
        if (sectores != null)
            return datosCompaniaRepository.countBySectorIn(sectores);
        return datosCompaniaRepository.countByIndustriaIn(industrias);
    }


    public Map<String, List<String>> recogerPaisesIndustriasYSectoresDisponibles() {
        List<String> paises = this.datosCompaniaRepository.findDistinctPaises();
        List<String> industrias = this.datosCompaniaRepository.findDistinctIndustrias();
        List<String> sectores = this.datosCompaniaRepository.findDistinctSectores();

        Map<String, List<String>> resultado = new LinkedHashMap<>();
        resultado.put("paises", paises);
        resultado.put("industrias", industrias);
        resultado.put("sectores", sectores);

        return resultado;
    }


}