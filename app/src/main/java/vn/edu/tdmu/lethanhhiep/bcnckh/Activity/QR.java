package vn.edu.tdmu.lethanhhiep.bcnckh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import vn.edu.tdmu.lethanhhiep.bcnckh.R;

public class QR extends AppCompatActivity {

    private ImageView img_qr;
    private Button btn_qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        addControl();
        addEvent();
    }
    private void addControl()
    {
        btn_qr = findViewById(R.id.btn_create);
        img_qr = findViewById(R.id.imageview);
    }
    private void addEvent()
    {
        btn_qr.setOnClickListener(new MyEvent());
    }
    private class MyEvent implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            if(view.equals(btn_qr))
            {
                Create_qr();
            }
        }
    }
    private void Create_qr()
    {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try{
            BitMatrix bitMatrix = multiFormatWriter.encode("123456789", BarcodeFormat.QR_CODE,500,500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            img_qr.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}