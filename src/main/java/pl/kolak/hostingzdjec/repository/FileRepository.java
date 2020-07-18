package pl.kolak.hostingzdjec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kolak.hostingzdjec.model.File;

public interface FileRepository extends JpaRepository<File,Long> {
}
