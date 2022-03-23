package vn.edu.tdmu.lethanhhiep.bcnckh.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import vn.edu.tdmu.lethanhhiep.bcnckh.R;

public class Info_user extends AppCompatActivity {
    private EditText edtten,edtmk,edtdc,edtns,edtsdt,txtkey;
    private TextView txtName,txtAddress;
    private Button btnluu;
    public Intent intent;
    private ImageView info_img_close;
    public Cursor cursor;
    private int kk = 0;
    private int key02 = 02;
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;
    public FirebaseAuth firebaseAuth =   FirebaseAuth.getInstance();
    public FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
   // private ArrayList<thongtinnguoidung> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("dangnhap");
        dangkynut();
        dangkysukien();
        ax();
    }
    private void dangkynut()
    {
        txtName = findViewById(R.id.info_tv_name);
        edtten = findViewById(R.id.info_edt_email);
        edtmk = findViewById(R.id.info_edt_password);
        edtdc = findViewById(R.id.info_edt_address);
        edtns = findViewById(R.id.info_edt_birth_day);
        edtsdt = findViewById(R.id.info_edt_phone_number);
        txtkey = findViewById(R.id.info_edt_key_chain);
        btnluu = findViewById(R.id.info_btn_save);
        info_img_close = findViewById(R.id.info_img_close);
        txtAddress = findViewById(R.id.tv_address);
        edtdc.setFocusable(false);
    }
    private void dangkysukien()
    {
        btnluu.setOnClickListener(new sukiencuatoi());
        info_img_close.setOnClickListener(new sukiencuatoi());
        txtName.setOnClickListener(new sukiencuatoi());
        edtdc.setOnClickListener(new sukiencuatoi());
    }
    private void sukiendong()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Info_user.this,R.style.AlertDialogStyle);
        builder.setTitle("Notification");
        builder.setMessage ("Do you want to exit ?");
        builder.setIcon(R.drawable.panda);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        Dialog dialog1 = builder.create();
        dialog1.show();
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    private void Askuser()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Info_user.this,R.style.AlertDialogStyle);
        builder.setTitle("Notification");
        builder.setMessage ("Do you want update address ?");
        builder.setIcon(R.drawable.panda);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                intent = new Intent(Info_user.this,Address_Us.class);
                startActivityForResult(intent, key02);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        Dialog dialog1 = builder.create();
        dialog1.show();
    }

    private void ax()
    {
        try {

            String id = MainActivity.tend;
            databaseReference.child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String Ma = snapshot.getKey();
                    txtName.setText(snapshot.child("Name").getValue().toString());
                    edtten.setText(snapshot.child("Tên").getValue().toString());
                    edtmk.setText( snapshot.child("Mật khẩu").getValue().toString());
                    edtns.setText(snapshot.child("Ngày sinh").getValue().toString());
                    edtdc.setText(snapshot.child("Địa chỉ").getValue().toString());
                    edtsdt.setText(snapshot.child("Số điện thoại").getValue().toString());
                    txtkey.setText(snapshot.child("Mã key").getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error)
                {
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(Info_user.this,"Data is conflicting, please login again",Toast.LENGTH_SHORT).show();
            intent = new Intent(Info_user.this,MainActivity.class);
            startActivity(intent);
        }

    }
    private void capnhatdl()
    {
        firebaseUser = firebaseAuth.getCurrentUser();
        String ten =  edtten.getText().toString();
        String mk  = edtmk.getText().toString();
        String ns = edtns.getText().toString();
        String dc = edtdc.getText().toString();
        String _sdt = edtsdt.getText().toString();
        if(mk.trim().length() < 8 || ns.trim().length() < 5 || dc.trim().length() < 5)
        {
            Toast.makeText(Info_user.this,"Update failed",Toast.LENGTH_SHORT).show();
        }
        else
        {
            HashMap<String,Object> result = new HashMap<>();
            result.put("Ngày sinh",ns);
            result.put("Mật khẩu",mk);
            result.put("Tên",ten);
            result.put("Địa chỉ",dc);
            result.put("Số điện thoại",_sdt);
            databaseReference.child(MainActivity.tend).updateChildren(result);
            firebaseUser.updatePassword(mk).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(Info_user.this,"Change password successfully",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }
    public void ExportFileExcel()
    {
        Dialog dialog;
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.alertdialog_setname);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = 1100;
        lp.height = 800;

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
    private class sukiencuatoi implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            if(view.equals(btnluu))
            {
                capnhatdl();
            }
            if(view.equals(info_img_close))
            {
                sukiendong();
            }
            if(view.equals(txtName))
            {
                ExportFileExcel();
            }
            if(view.equals(edtdc))
            {
                Askuser();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode == key02 && resultCode == RESULT_OK && data != null)
        {
            Bundle bundle = data.getBundleExtra("dcc");
            String dc = bundle.getString("dcn");
            edtdc.setText(dc);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}