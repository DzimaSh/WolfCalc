package by.bsu.fileservice.rest;

import by.bsu.fileservice.model.MyFile;
import by.bsu.fileservice.exception.MyFileNotFoundException;
import by.bsu.fileservice.service.MyFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/files")
public class FileController {

    private final MyFileService myFileService;

    @GetMapping("/{filename}")
    public MyFile getFile(@PathVariable String filename) throws MyFileNotFoundException {
        return myFileService.getFile(filename);
    }
}
