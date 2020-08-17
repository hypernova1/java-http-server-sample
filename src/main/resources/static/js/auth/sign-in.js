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
                alert('이메일을 입력해주세요.');
                return;
            }
            if (this.form.password.length === 0) {
                alert('비밀번호를 입력해주세요.');
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
                    alert('로그인 되었습니다.');
                    vm.loginUser = data;
                    router.push("/");
                })
                .catch((err) => alert(err));
        }
    }
});