package by.bsu.fileservice.service;

import by.bsu.fileservice.exception.MyFileNotFoundException;
import by.bsu.fileservice.model.MyFile;
import by.bsu.fileservice.repository.MyFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyFileServiceImpl implements MyFileService {

    private final MyFileRepository myFileRepository;

    @Override
    public MyFile getFile(String filename) throws MyFileNotFoundException {
        return myFileRepository.findById(filename)
                .orElseThrow(() -> new MyFileNotFoundException(String.format("File with name '%s' not found", filename)));
    }

    @Override
    public MyFile saveFile(MyFile myFile) {
        return myFileRepository.save(myFile);
    }
}
