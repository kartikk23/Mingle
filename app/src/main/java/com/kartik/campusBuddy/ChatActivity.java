package com.kartik.campusBuddy;

import androidx.appcompat.app.AppCompatActivity;

import com.kartik.campusBuddy.databinding.ActivityChatBinding;

public class ChatActivity extends AppCompatActivity {
//    ActivityChatBinding binding;
//    FirebaseAuth auth;
//    FirebaseUser fUser;
//    FirebaseDatabase database;
//    String uid;
//    ArrayList<messageModel> messageModels = new ArrayList<>();
//    APIService apiService;
//    boolean notify = false;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityChatBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        auth = FirebaseAuth.getInstance();
//        fUser = auth.getCurrentUser();
//        database = FirebaseDatabase.getInstance();
//
//        uid = getIntent().getStringExtra("userId");
//        String uName = getIntent().getStringExtra("userName");
//
//        binding.tvUserName.setText(uName);
//
//        final String senderId = auth.getUid();
//        String rec_id = uid;
//        String userName = uName;
//
//        ChatAdapter chatAdapter = new ChatAdapter(messageModels, this, uid);
//
//        binding.chatsRecView.setLayoutManager(new LinearLayoutManager(this));
//        binding.chatsRecView.setAdapter(chatAdapter);
//
//        String senderRoom = senderId+rec_id;
//        String receiverRoom = rec_id+senderId;
//
//        apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);
//
//
//        database.getReference().child("chats").child(senderRoom).addValueEventListener(new ValueEventListener(){
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                messageModels.clear();
//                for (DataSnapshot snapshot1: snapshot.getChildren()){
//                    messageModel model = snapshot1.getValue(messageModel.class);
//                    model.setMessageId(snapshot1.getKey());
//                    messageModels.add(model);
//                }
//                chatAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        binding.btnSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Long stamp = new Date().getTime();
//                stamp = stamp/1000;
//                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//                String strDate  = sdf.format(new Date(stamp*1000));
//                sendMessage(senderId, rec_id, binding.etChat.getText().toString(), strDate);
//            }
//        });
//
//
////        binding.btnSend.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                String msg = binding.etChat.getText().toString();
////                Long stamp = new Date().getTime();
////                messageModel model = new messageModel(senderId, msg,stamp );
////                binding.etChat.setText("");
////                database.getReference().child("chats").child(senderRoom).
////                        push().setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
////                            @Override
////                            public void onComplete(@NonNull Task<Void> task) {
////                                database.getReference().child("chats").child(receiverRoom)
////                                        .push()
////                                        .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
////                                            @Override
////                                            public void onSuccess(Void unused) {
////                                            }
////                                        });
////                            }
////                        });
////            }
////        });
//    }
//
//
//    // METHID FOR SENDING MESSSAGE-------------
//
//    private void sendMessage(String sender, final String receiver, String message, String time){
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//        HashMap<String, Object> hashMap = new HashMap<>();
//        hashMap.put("sender", sender);
//        hashMap.put("receiver", receiver);
//        hashMap.put("message", message);
//        hashMap.put("isseen", false);
//        hashMap.put("time", time);
//
//        reference.child("Chats").push().setValue(hashMap);
//
//
//        // add user to chat fragment
//        final DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("Chatlist")
//                .child(fUser.getUid())
//                .child(uid);
//
//        chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (!dataSnapshot.exists()){
//                    chatRef.child("id").setValue(uid);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        });
//
//        final DatabaseReference chatRefReceiver = FirebaseDatabase.getInstance().getReference("Chatlist")
//                .child(uid)
//                .child(fUser.getUid());
//        chatRefReceiver.child("id").setValue(fUser.getUid());
//
//        final String msg = message;
//
//        reference = FirebaseDatabase.getInstance().getReference("Users").child(fUser.getUid());
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Users user = dataSnapshot.getValue(Users.class);
//                if (notify) {
//                    sendNotification(receiver, user.getUsername(), msg);
//                }
//                notify = false;
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//
//    // METHOD FOR SENDING NOTIFICATION
//    private void sendNotification(String receiver, final String username, final String message){
//        DatabaseReference tokens = FirebaseDatabase.getInstance().getReference("Tokens");
//        Query query = tokens.orderByKey().equalTo(receiver);
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    Token token = snapshot.getValue(Token.class);
//                    Data data = new Data(fUser.getUid(), R.drawable.user_icon, username+": "+message, "New Message",
//                            uid);
//
//                    Sender sender = new Sender(data, token.getToken());
//
//                    apiService.sendNotification(sender)
//                            .enqueue(new Callback<MyResponse>() {
//                                @Override
//                                public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
//                                    if (response.code() == 200){
//                                        if (response.body().success != 1){
//                                            //Toast.makeText(MessageActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<MyResponse> call, Throwable t) {
//
//                                }
//                            });
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        });
//    }
}