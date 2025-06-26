    package cl.gdl.ms_pedido.entity;

    import jakarta.persistence.*;
    import lombok.*;

    @Entity
    @Table(name = "COMUNA")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class ComunaEntity {

        @Id
        @Column(name = "ID_COMUNA")
        private Long id;

        @Column(name = "NOMBRE", nullable = false, length = 100)
        private String nombre;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "ID_REGION", nullable = false)
        private RegionEntity region;
    }
