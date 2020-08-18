const SignInForm = Vue.component('sign-in-form', {
    template: `
            <div>
                <h1>sign-in</h1>
                <div>
                    <label for="email">email:
                        <input type="text" name="email" v-model="form.email"></label>
                </div>
                <div>
                    <label for="email">password:
                        <input type="password" name="password" v-model="form.password">
                    </label>
                </div>
                <button @click="signIn">submit</button>
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
                    router.push("/");
                })
                .catch((err) => alert(err));
        }
    }
});