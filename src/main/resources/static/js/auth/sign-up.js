const SignUpPage = Vue.component('sign-up-form', {
    template: `
            <div class="sign-up-container">
  <h1>Sign Up</h1>
  <div class="form-group">
    <label for="email">Email</label>
    <input type="text" name="email" v-model="form.email" @change="checkEmail" placeholder="Enter your email">
  </div>
  <div class="form-group">
    <label for="name">Name</label>
    <input type="text" name="name" v-model="form.name" placeholder="Enter your name">
  </div>
  <div class="form-group">
    <label for="password">Password</label>
    <input type="password" name="password" v-model="form.password" placeholder="Enter your password">
  </div>
  <div class="form-group">
    <button @click="signUp">Submit</button>
  </div>
</div>

        `,
    data() {
        return {
            form: {
                email: '',
                name: '',
                password: '',
            },
            validate: {
                email: false,
            }
        }
    },
    methods: {
        checkEmail() {
            if (!this.form.email) return;
            fetch(`/auth/check-email/${this.form.email}`)
                .then((response) => {
                    if (response.status !== 200) {
                        alert('email already exist.');
                        return;
                    }
                    this.validate.email = true;
                })
                .catch((e) => {
                    console.error(e);
                    alert('server error.')
                    this.validate.email = false;
                });
        },
        signUp() {
            if (this.form.email.length === 0) {
                alert('email cannot be empty.');
                return;
            }
            if (!this.validate.email) {
                alert('email already exist.');
                return;
            }
            if (this.form.name.length === 0) {
                alert('name cannot be empty.');
                return;
            }
            if (this.form.password.length === 0) {
                alert('password cannot be empty.');
                return;
            }
            fetch('/auth/join', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(this.form),
            })
                .then(() => {
                    alert('sign-up success!');
                    router.push("/sign-in");
                })
                .catch((err) => alert(err));
        }
    }
});