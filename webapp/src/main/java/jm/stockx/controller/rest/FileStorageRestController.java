package jm.stockx.controller.rest;

import jm.stockx.ItemService;
import jm.stockx.entity.Item;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@RestController
public class FileStorageRestController {
    @Value("${upload.path}")
    private String uploadPath;

    private ItemService itemService;

    @Autowired
    public FileStorageRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    private String hashGenerator(String src) {
        return DigestUtils.md5Hex(src);
    }


    @PostMapping("/img/upload")
    public String uploadItemPicture(@RequestParam("id") Long id,
                                    MultipartFile file) throws IOException {
        if (file != null) {
            Item item = itemService.getItemById(id);
            File uploadDir = new File(uploadPath);
            String filePath = uploadPath + "item_" + item.getId() + ".png";
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            file.transferTo(Path.of(filePath));
            item.setItemImageUrl(filePath);
            itemService.update(item);
            return Path.of(filePath).toAbsolutePath() + hashGenerator(file.getName());
        }
        return "Uploading failed";
    }
}
