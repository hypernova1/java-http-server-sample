const SignInPage = Vue.component('sign-in-form', {
    template: `
            <div class="sign-in-container">
  <h1>Sign In</h1>
  <div class="form-group">
    <label for="email">Email</label>
    <input type="text" name="email" v-model="form.email" placeholder="Enter your email">
  </div>
  <div class="form-group">
    <label for="password">Password</label>
    <input type="password" name="password" v-model="form.password" placeholder="Enter your password">
  </div>
  <button @click="signIn">Submit</button>
</div>
        `,
    data() {
        return {
            form: {
                email: '',
                password: '',
            },
            validate: {
                email: false,
            }
        }
    },
    methods: {
        signIn() {
            if (this.form.email.length === 0) {
                alert('email cannot be empty.');
                return;
            }
            if (this.form.password.length === 0) {
                alert('password cannot be empty.');
                return;
            }
            fetch('/auth/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(this.form),
            }).then((res) => {
                if (res.status !== 200) {
                    throw new Error(res.status);
                }
                return res.json()
            })
                .then((data) => {
                    alert('sign-in succeed.');
                    vm.loginUser = data;
                    router.push("/post");
                })
                .catch((err) => alert(err));
        }
    }
});