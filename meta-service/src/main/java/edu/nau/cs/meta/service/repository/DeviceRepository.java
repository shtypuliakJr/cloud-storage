package edu.nau.cs.meta.service.repository;

import edu.nau.cs.meta.service.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {
}
