package cnab.normalization.bycoders.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class HandleFileServiceImpl implements StorageService {
    @Override
    public void store(MultipartFile file) {
        System.out.println(file);
    }
}
