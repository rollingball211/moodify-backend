package com.rollingball211.moodify_backend.dto.User;
public class UserResponseDTO {

       private Long id;  //JPA에서의 기본 PK,

        private String username;
        private String email;

        public UserResponseDTO( Long id, String username, String email) {
            this.id = id;
            this.username = username;
            this.email = email;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

}
