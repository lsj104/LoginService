<template>
  <div>
    <!-- Header -->
    <div class="header bg-gradient-success py-7 py-lg-8 pt-lg-9">
      <b-container class="container">
        <div class="header-body text-center mb-7">
          <b-row class="justify-content-center">
            <b-col xl="5" lg="6" md="8" class="px-5">
              <h1 class="text-white">Create an account</h1>
            </b-col>
          </b-row>
        </div>
      </b-container>
      <div class="separator separator-bottom separator-skew zindex-100">
        <svg
          x="0"
          y="0"
          viewBox="0 0 2560 100"
          preserveAspectRatio="none"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
        >
          <polygon
            class="fill-default"
            points="2560 0 2560 100 0 100"
          ></polygon>
        </svg>
      </div>
    </div>
    <!-- Page content -->
    <b-container class="mt--8 pb-5">
      <!-- Table -->
      <b-row class="justify-content-center">
        <b-col lg="6" md="8">
          <b-card no-body class="bg-secondary border-0">
            <b-card-header class="bg-transparent pb-5"> </b-card-header>
            <b-card-body class="px-lg-5 py-lg-5">
              <validation-observer
                v-slot="{ handleSubmit }"
                ref="formValidator"
              >
                <b-form role="form" @submit.prevent="handleSubmit(onSubmit)">
                  <base-input
                    alternative
                    class="mb-3"
                    placeholder="Username"
                    name="username"
                    :rules="{ required: true }"
                    v-model="model.username"
                  >
                  </base-input>

                  <!-- <base-input
                    alternative
                    class="mb-3"
                    placeholder="Nickname"
                    name="nickname"
                    :rules="{ required: true }"
                    v-model="model.nickname"
                  >
                  </base-input> -->

                  <base-input
                    alternative
                    class="mb-3"
                    placeholder="Email"
                    name="email"
                    :rules="{ required: true, email: true }"
                    v-model="model.email"
                  >
                  </base-input>

                  <base-input
                    alternative
                    class="mb-3"
                    placeholder="Password"
                    type="password"
                    name="password"
                    :rules="{ required: true, min: 6 }"
                    v-model="model.password"
                  >
                  </base-input>

                  <div class="text-center">
                    <b-button type="submit" variant="primary" class="mt-4"
                      >Create account</b-button
                    >
                  </div>
                </b-form>
              </validation-observer>
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "register",
  data() {
    return {
      model: {
        username: "", // 사용자가 입력한 이름
        email: "", // 사용자가 입력한 이메일
        password: "", // 사용자가 입력한 비밀번호
        // nickname: "", // 사용자가 입력한 닉네임
        role: "MASTER",
      },
    };
  },
  methods: {
    onSubmit() {
      // 회원가입 API 호출
      axios
        .post("http://localhost:8080/api/v1/users/sign-up", {
          username: this.model.username,
          email: this.model.email,
          password: this.model.password,
          nickname: this.model.nickname,
          role: this.model.role,
        })
        .then((response) => {
          alert("회원가입이 완료됐습니다. 로그인 후 이용해 주세요.");
          this.$router.push({ name: "login" }); // 회원가입 성공 후 로그인 페이지로 이동
        })
        .catch((error) => {
          console.error("Sign up failed:", error);
          alert(
            "Sign up failed: " +
              (error.response ? error.response.data.message : error.message)
          );
        });
    },
  },
};
</script>

<style>
/* 스타일을 여기에 추가할 수 있습니다 */
</style>
