package finanzas_pro.repositories;

import finanzas_pro.models.entities.DatosCompañia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DatosCompaniaRepository extends JpaRepository<DatosCompañia, String> {

    DatosCompañia findByCompanySymbol(String companySymbol);

    Page<DatosCompañia> findBySector(String sector, Pageable page);

    Page<DatosCompañia> findByPais(String pais, Pageable page);

    Page<DatosCompañia> findByBolsa(String bolsa, Pageable page);

    Page<DatosCompañia> findByIndustria(String industria, Pageable page);

    Page<DatosCompañia> findByPaisAndSector(String pais, String sector, Pageable page);


    // ===== Filtros combinados =====
    Page<DatosCompañia> findByPaisIn(List<String> paises, Pageable page);

    Page<DatosCompañia> findBySectorIn(List<String> sectores, Pageable page);

    Page<DatosCompañia> findByIndustriaIn(List<String> industrias, Pageable page);

    Page<DatosCompañia> findByPaisInAndSectorIn(List<String> paises, List<String> sectores, Pageable page);

    Page<DatosCompañia> findByPaisInAndIndustriaIn(List<String> paises, List<String> industrias, Pageable page);

    Page<DatosCompañia> findBySectorInAndIndustriaIn(List<String> sectores, List<String> industrias, Pageable page);

    Page<DatosCompañia> findByPaisInAndSectorInAndIndustriaIn(List<String> paises, List<String> sectores, List<String> industrias, Pageable page);

    // ===== Contadores =====
    @Query("SELECT DISTINCT d.pais FROM DatosCompañia d ORDER BY d.pais ASC")
    List<String> findDistinctPaises();

    @Query("SELECT DISTINCT d.industria FROM DatosCompañia d ORDER BY d.industria ASC")
    List<String> findDistinctIndustrias();

    @Query("SELECT DISTINCT d.sector FROM DatosCompañia d ORDER BY d.sector ASC")
    List<String> findDistinctSectores();

    int countByPais(String pais);

    int countBySector(String sector);

    int countByBolsa(String bolsa);

    int countByIndustria(String industria);

    long countByPaisInAndSectorInAndIndustriaIn(List<String> paises, List<String> sectores, List<String> industrias);

    long countByIndustriaIn(List<String> industrias);

    long countByPaisIn(List<String> paises);

    long countBySectorIn(List<String> sectores);

    long countByPaisInAndSectorIn(List<String> paises, List<String> sectores);

    long countByPaisInAndIndustriaIn(List<String> paises, List<String> industrias);

    long countBySectorInAndIndustriaIn(List<String> sectores, List<String> industrias);
}
