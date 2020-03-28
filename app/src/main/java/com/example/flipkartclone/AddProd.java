package com.example.flipkartclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.flipkartclone.Models.ProductMobile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProd extends AppCompatActivity {

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prod);

        EditText Name = findViewById(R.id.Name);
        EditText company = findViewById(R.id.Company);
        EditText FC = findViewById(R.id.FrontC);
        EditText BC  = findViewById(R.id.BackCam);
        EditText RAM = findViewById(R.id.RAM);
        EditText Display = findViewById(R.id.display);


        EditText Screen = findViewById(R.id.Screensize);
        EditText Battery = findViewById(R.id.battery);
        EditText Processor = findViewById(R.id.Processor);

        EditText Price = findViewById(R.id.Price);
        EditText ROM = findViewById(R.id.ROM);
        EditText color = findViewById(R.id.Color);

        EditText Expand = findViewById(R.id.Expand);
        EditText URL = findViewById(R.id.URL);
        EditText offset = findViewById(R.id.Offset);

        reference = FirebaseDatabase.getInstance().getReference();

        int[] A = new int[50];

        String N1[] = {"Redmi 8", "Redmi Note 7S", "Redmi 8A", "Redmi Note 7 Pro", "Redmi 8i","Mi A2","Realme 5s","Relame 4s","Realme 5s","Realme 5 Pro","Realme 4 Pro","Infinix s5 lite","Apple iPhone 7","Apple iPhone 8","Honor 1","Honor 2","Honor 3","Honor 4","Honor 5","Lenovo 1","Lenovo 2","Lenovo 3","Lenovo 4","Apple Iphone 1","Apple Iphone 2","Realme X2 Pro","Realme Pro 4","OPPO 1","OPPO 2","OPPO 3","OPPO 4","OPPO 5","Redmi k20 Pro","Vivo 1","Vivo2","Vivo 3","Vivo 4","Vivo 5","Nokia 1","Nokia 2","Nokia 3","Nokia 4"," OnePlus 1","OnePlus 2","OnePlus 3","OnePlus 4","OnePlus 7 Pro","OnePlus 8 Pro","Asus Zenfone Max Pro M1","Asus Zenfone Max Pro M2"};
        String N2[]= {"Redmi","Redmi","Redmi","Redmi","Redmi","Redmi","Realme","Realme","Realme","Realme","Realme","Infinix","Apple","Apple","Honor","Honor","Honor","Honor","Honor","Lenovo","Lenovo","Lenovo","Lenovo","Apple","Apple","Realme","Realme","Oppo","Oppo","Oppo","Oppo","Oppo","Redmi","Vivo","Vivo","Vivo","Vivo","Vivo","Nokia","Nokia","Nokia","Nokia","Oneplus","Oneplus","Oneplus","Oneplus","Oneplus","Oneplus","Asus","Asus"};
        String N3[]={"8MP","13MP","8MP","13MP","5MP","8MP","13MP","8MP","13MP","5MP","8MP","13MP","8MP","13MP","5MP","8MP","13MP","8MP","13MP","5MP","8MP","13MP","8MP","13MP","5MP","8MP","13MP","8MP","13MP","5MP","8MP","13MP","8MP","13MP","5MP","8MP","13MP,","8MP","13MP","5MP","8MP","13MP","8MP","13MP","5MP","8MP","13MP","8MP","13MP","5MP" };
        String N4[]={"12MP+2MP","48MP+5MP","12MP","48MP+5MP","35MP+12MP","12MP+2MP","48MP+5MP","12MP","48MP+5MP","35MP+12MP","12MP+2MP","48MP+5MP","12MP","48MP+5MP","35MP+12MP","12MP+2MP","48MP+5MP","12MP","48MP+5MP","35MP+12MP","12MP+2MP","48MP+5MP","12MP","48MP+5MP","35MP+12MP","12MP+2MP","48MP+5MP","12MP","48MP+5MP","35MP+12MP","12MP+2MP","48MP+5MP","12MP","48MP+5MP","35MP+12MP","12MP+2MP","48MP+5MP","12MP","48MP+5MP","35MP+12MP","12MP+2MP","48MP+5MP","12MP","48MP+5MP","35MP+12MP 12MP+2MP","48MP+5MP","12MP","48MP+5MP","35MP+12MP" };
        String N5[]={"4GB","4GB", "3GB", "4GB", "4GB", "6GB", "4GB", "4GB", "3GB", "4GB", "4GB", "6GB", "4GB", "4GB","3GB", "4GB", "4GB", "6GB", "4GB", "4GB", "3GB", "4GB", "4GB", "6GB", "4GB", "4GB", "3GB", "4GB","4GB", "6GB", "4GB", "4GB", "3GB", "4GB","4GB", "6GB", "4GB", "4GB", "8GB", "8GB", "8GB", "8GB", "4GB", "4GB", "3GB", "4GB", "4GB", "6GB","4GB","4GB" };
        String N6 = "HD+";
        String N7[] = {"6.22", "6.3", "6.22", "6.3", "6.7", "6.7", "6.8", "6.5", "6.4", "6.3", "6.2", "6.1", "6", "5.99", "5.8", "5.7", "5.8", "5.7", "5.6", "5.4", "5.4", "53", "5.3", "5.2", "5.1", "4.9", "5.1", "4.8", "4.1", "4.8", "4.7", "4.1", "4.8", "5.2", "53", "6.2", "6.1", "6", "5.99", "5.8", "5.7", "5.8", "5.7", "5.6", "5.4", "5.4", "53", "5.3", "5.2", "5.1" };
        String N8[]={"5000", "4000", "5000", "3500", "6000", "3000", "3100", "3200", "5000", "4258", "4300", "4800", "3000", "2900", "4666", "4600", "5702", "5000", "4000", "5000", "3500", "6000", "3000", "3100", "3200", "5000", "4258", "4300", "4800", "3000", "2900", "4666", "4600", "5702", "5000", "3500", "6000", "3000", "3100", "3200", "5000", "4258", "4300", "4800", "3000", "2900", "4666", "4600", "5702", "4020" };
        String N9[]={"Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 660 AIE Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 660 AIE Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 660 AIE Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 660 AIE Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 660 AIE Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 660 AIE Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 660 AIE Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 660 AIE Processor", "Qualcomm Snapdragon 439 Processor", "Helix", "Helix", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 660 AIE Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 660 AIE Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 660 AIE Processor", "Qualcomm Snapdragon 439 Processor", "Helix", "Helix", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 660 AIE Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 660 AIE Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 439 Processor", "Qualcomm Snapdragon 660 AIE Processor", "Qualcomm Snapdragon 439 Processor", "Helix", "Helix", "Qualcomm","Helix",};
        String N10[]={"7999", "9999", "6999", "4000", "8000", "9999", "14999", "14999", "15999", "18999", "12999", "10999", "9999", "7999", "9999", "6999", "4000", "8000", "9999", "14999", "14999", "15999", "18999", "12999", "10999", "9999", "7999", "9999", "6999", "4000", "8000", "9999", "14999", "14999", "15999", "18999", "12999", "10999", "9999", "7999", "9999", "6999", "4000", "8000", "9999", "14999", "14999", "15999", "18999", "12999" };
        String N11[]={"64GB", "64GB", "32GB", "64GB", "32GB", "64GB", "64GB", "32GB", "64GB", "32GB", "64GB", "64GB", "32GB", "64GB", "32GB", "64GB", "64GB", "32GB", "64GB", "32GB", "64GB", "64GB", "32GB", "64GB", "32GB", "64GB", "32GB", "64GB", "32GB", "64GB", "64GB", "32GB", "64GB", "32GB", "64GB", "64GB", "32GB", "64GB", "32GB", "64GB", "32GB", "64GB", "32GB", "64GB", "64GB", "32GB", "64GB", "32GB", "64GB", "64GB",};
        String N12[]={"Black", "Blue", "Blue", "Blue", "Green", "Orange", "Red", "Pink", "Pink", "White", "Lemon", "Black", "Blue", "Blue", "Blue", "Green", "Orange", "Red", "Pink", "Pink", "White", "Lemon", "Black", "Blue", "Blue", "Blue", "Green", "Orange", "Red", "Pink", "Pink", "White", "Lemon", "Black", "Blue", "Blue", "Blue", "Green", "Orange", "Red", "Pink", "Pink", "White", "Lemon", "Black", "Blue", "Blue", "Blue", "Green", "Orange" };
        String N13[] = {"512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB", "512GB", "256GB" };
        String N14[]={"https://rukminim1.flixcart.com/image/312/312/k1fbmvk0/mobile/4/f/f/mi-redmi-8-mzb8251in-original-imafhyacmxaefxgw.jpeg?q=70", "https://rukminim1.flixcart.com/image/312/312/k612pow0/mobile/b/s/r/realme-c3-rmx2027-original-imafzkzhts5kfmxt.jpeg?q=70", "https://rukminim1.flixcart.com/image/312/312/k7dnonk0/mobile/r/r/t/realme-6-rbs0601in-original-imafpmg2aqdf2dqm.jpeg?q=70", "https://rukminim1.flixcart.com/image/312/312/k0lbdzk0pkrrdj/mobile/z/j/a/mi-redmi-note-7-pro-mzb7465in-original-imafhvrdmeyweauc.jpeg?q=70", "https://rukminim1.flixcart.com/image/312/312/k65d18w0/mobile/4/k/4/poco-x2-mzb9011in-original-imafzz2hbfkvftm5.jpeg?q=70" };
        int  N15[]={10,9,8,7,6,5,15,12,11,18,10,9,8,7,6,5,15,12,11,18,10,9,8,7,6,5,15,12,11,18,10,9,8,7,6,5,15,12,11,18,10,9,8,7,6,5,15,12,11,18};


        for(int i = 38;i<50;i++)
        {
            ProductMobile qw = new ProductMobile(N1[i], N2[i], N3[i],N4[i], N5[i], N6,N7[i],N8[i],N9[i],N10[i],N11[i],N12[i],N13[i],N14[i%5],0,0,0,0,0,N15[i],5);
            reference.child("ProductMobile").child(qw.getName()).setValue(qw)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(AddProd.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
