package com.example.service_venue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.service_venue.utils.AndroidUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class VendorSignupOTP extends AppCompatActivity {

    String phoneNumber;
    Long timeoutSeconds = 60L;
    String verificationCode;
    PhoneAuthProvider.ForceResendingToken resendingToken;

    EditText vendor_signup_otp_otpEdt;
    Button vendor_signup_otp_nextBtn;
    ProgressBar progressBar;
    TextView vendor_signup_otp_resend;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_signup_otp);

        vendor_signup_otp_otpEdt = findViewById(R.id.vendor_signup_otp_otpEdt);
        vendor_signup_otp_nextBtn = findViewById(R.id.vendor_signup_otp_nextBtn);
        progressBar = findViewById(R.id.vendor_signup_otp_progressbar);
        vendor_signup_otp_resend = findViewById(R.id.vendor_signup_otp_resend);

        phoneNumber = getIntent().getExtras().getString("phone");

        sendOtp(phoneNumber, false);

        vendor_signup_otp_nextBtn.setOnClickListener(v -> {
            String enteredOtp = vendor_signup_otp_otpEdt.getText().toString();
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, enteredOtp);
            signIn(credential);
        });

        vendor_signup_otp_resend.setOnClickListener((v) -> {
            sendOtp(phoneNumber, true);
        });

    }

    void sendOtp(String phoneNumber, boolean isResend) {
        if (!isResend) {
            // Show progress bar only if it's not a resend
            setInProgress(true);
        }
        startResendTimer(); // Start timer for resend option

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                timeoutSeconds,
                TimeUnit.SECONDS,
                this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signIn(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        AndroidUtil.showToast(getApplicationContext(), "OTP verification failed");
                        setInProgress(false);
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verificationCode = s;
                        resendingToken = forceResendingToken;
                        AndroidUtil.showToast(getApplicationContext(), "OTP sent successfully");
                        setInProgress(false); // Hide progress bar after OTP is sent
                    }
                }
        );
    }

    void setInProgress(boolean inProgress) {
        if (inProgress) {
            progressBar.setVisibility(View.VISIBLE);
            vendor_signup_otp_nextBtn.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            vendor_signup_otp_nextBtn.setVisibility(View.VISIBLE);
        }
    }

    void signIn(PhoneAuthCredential phoneAuthCredential) {
        //login and go to next activity
        setInProgress(true);
        mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                setInProgress(false);
                if (task.isSuccessful()) {
                    Intent intent = new Intent(VendorSignupOTP.this, MainActivity.class);
                    intent.putExtra("phone", phoneNumber);
                    startActivity(intent);
                } else {
                    AndroidUtil.showToast(getApplicationContext(), "OTP verification failed");
                }
            }
        });
    }

    void startResendTimer() {
        vendor_signup_otp_resend.setEnabled(false);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeoutSeconds--;
                final String timeRemaining = "Resend OTP in " + timeoutSeconds + " seconds";
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        vendor_signup_otp_resend.setText(timeRemaining);
                    }
                });
                if (timeoutSeconds <= 0) {
                    timeoutSeconds = 60L;
                    timer.cancel();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            vendor_signup_otp_resend.setEnabled(true);
                        }
                    });
                }
            }
        }, 0, 1000);
    }
}
