package com.dtrung.chatapp.repository;

import com.dtrung.chatapp.model.FriendShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FriendShipRepository extends JpaRepository<FriendShip, UUID> {

    @Query("SELECT f from FriendShip f where (f.senderId = :userId " +
            "or f.receiverId = :userId) and f.status = 'ACCEPTED'")
    List<FriendShip> findByUserId(@Param("userId") UUID userId);

    boolean existsBySenderIdAndReceiverId(UUID senderId, UUID receiverId);

    @Query("SELECT f from FriendShip f where f.id = :id and f.receiverId = :receiverId ")
    FriendShip findByIdAndReceiverId(UUID id, UUID receiverId);
}
