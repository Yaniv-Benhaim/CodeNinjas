package tech.gamedev.codeninjas.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.messaging.FirebaseMessaging;

import tech.gamedev.codeninjas.data.retrofit.NotificationModel;
import tech.gamedev.codeninjas.data.retrofit.PushNotification;
import tech.gamedev.codeninjas.databinding.ActivityAdminBinding;
import tech.gamedev.codeninjas.other.Constants;
import tech.gamedev.codeninjas.repo.NotificationRepo;

public class AdminActivity extends AppCompatActivity {

    private ActivityAdminBinding binding;
//    private AdminViewModel viewModel;
    private NotificationRepo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        repo = new NotificationRepo();
        FirebaseMessaging.getInstance().subscribeToTopic(Constants.NOTIFICATION_TOPIC);
        /*viewModel = new ViewModelProvider(this).get(AdminViewModel.class);*/
        binding.btnSendNotification.setOnClickListener(view1 -> {
            createNotification();
        });
    }

    private void sendNotification(PushNotification notification) {
        repo.sendNotification(notification);
    }

    private void createNotification() {
        String title = binding.etTitle.getText().toString();
        String message = binding.etMessage.getText().toString();
        String token = binding.etToken.getText().toString();

        PushNotification notification = new PushNotification(
                new NotificationModel(title, message),
                Constants.NOTIFICATION_TOPIC);
        Log.d(Constants.NOTIFICATION_TAG, notification.toString());
        sendNotification(notification);
    }
}