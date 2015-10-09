package com.ocr.thinning;

import org.opencv.core.Mat;

/**
 * Created by Yusfia Hafid A on 9/18/2015.
 */
public class Histogram {
    public int lv[][] = new int[4][256];//0 = R, 1 = G, 2 = B, 4 = Gray
    byte distribution[] = new byte[256 * 256 * 256];
    public int uniqueColor;

    public void setHistogram(Mat image) {
        int row = image.rows();
        int col = image.cols();
        uniqueColor = 0;
        int colour[] = new int[3];
        for (int i = 0; i < row; i++) {
            Mat scnLine = image.row(i);
            for (int j = 0; j < col; j++) {
                byte[] tinyimg = new byte[3];
                scnLine.get(0, j, tinyimg);
                colour[0] = Byte.toUnsignedInt(tinyimg[0]);
                colour[1] = Byte.toUnsignedInt(tinyimg[1]);
                colour[2] = Byte.toUnsignedInt(tinyimg[2]);
                byte temp = distribution[colour[0] * 256 * 256 + colour[1] * 256 + colour[2]];
                distribution[colour[0] * 256 * 256 + colour[1] * 256 + colour[2]] = 1;
                if (distribution[colour[0] * 256 * 256 + colour[1] * 256 + colour[2]] == 1 && temp == 0)
                    uniqueColor++;
                lv[0][colour[0]]++;
                lv[1][colour[1]]++;
                lv[2][colour[2]]++;
                lv[3][(int) ((colour[0] + colour[1] + colour[2]) / 3)]++;
            }
        }
    }
}
