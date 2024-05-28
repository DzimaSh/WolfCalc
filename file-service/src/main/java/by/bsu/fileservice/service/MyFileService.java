package by.bsu.fileservice.service;

import by.bsu.fileservice.model.MyFile;
import by.bsu.fileservice.exception.MyFileNotFoundException;

public interface MyFileService {

    MyFile getFile(String filename) throws MyFileNotFoundException;

    MyFile saveFile(MyFile myFile);
}
