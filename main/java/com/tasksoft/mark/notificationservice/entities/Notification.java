package com.tasksoft.mark.notificationservice.entities;

import com.tasksoft.mark.notificationservice.entities.enums.NotificationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "notification_type", nullable = false)
    private NotificationType notificationType;

    @Column(name = "recipient_id")
    private Long recipientId;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "task_id", nullable = false)
    private Long taskId;

    // --- HANDLING THE JOIN TABLE WITHOUT A USER ENTITY ---
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "notification_reads", // Matches your Flyway schema
            joinColumns = @JoinColumn(name = "notification_id")
    )
    @Column(name = "user_id") // Maps to the user_id column in the join table
    private Set<Long> readByUserIds = new HashSet<>();
}
