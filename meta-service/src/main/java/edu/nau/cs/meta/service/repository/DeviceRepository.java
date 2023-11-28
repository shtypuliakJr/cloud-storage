package edu.nau.cs.meta.service.repository;

import edu.nau.cs.meta.service.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {

    List<Device> findAllByUserId(String userId);

    Optional<Device> findByIdAndUserId(String id, String userId);

    void deleteByIdAndUserId(String id, String userId);

}
