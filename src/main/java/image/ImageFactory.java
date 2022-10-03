package image;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import utils.Constants;
import javax.swing.ImageIcon;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageFactory {

  public static ImageIcon createImage(ImageType imageType) {

    ImageIcon imageIcon = null;

    if (imageType == ImageType.LOGO) {
      imageIcon = new ImageIcon(Constants.LOGO_URL);
    } else if (imageType == ImageType.BACKGROUND) {
      imageIcon = new ImageIcon(Constants.BACKGROUND_URL);
    } else if (imageType == ImageType.LASER) {
      imageIcon = new ImageIcon(Constants.LASER_URL);
    }

    return imageIcon;
  }
}
