const SignUpForm = Vue.component('sign-up-form', {
    template: `
            <div>
                <h1>sign-up</h1>
                <div>
                    <label for="email">email:
                        <input type="text" name="email" v-model="form.email" @change="checkEmail"></label>
                </div>
                <div>
                    <label for="name">name:
                        <input type="text" name="name" v-model="form.name">
                    </label>
                </div>
                <div>
                    <label for="email">password:
                        <input type="password" name="password" v-model="form.password">
                    </label>
                </div>
                <button @click="signUp">submit</button>
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
                .then((res) => res.json())
                .then(() => {
                    this.validate.email = true;
                })
                .catch(() => {
                    alert('email already exist.')
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
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(this.form),
            }).then((res) => res.json())
                .then(() => {
                    alert('sign-up success!');
                    router.push("/");
                })
                .catch((err) => alert(err));
        }
    }
});