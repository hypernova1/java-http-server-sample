const routes = [
    { path: '/board', component: BoardList, props: true },
    { path: '/board/new', component: BoardInsertForm },
    { path: '/board/:id', component: BoardDetail },
    { path: '/sign-up', component: SignUpForm },
    { path: '/sign-in', component: SignInForm },
];
const router = new VueRouter({
    routes,
});

const vm = new Vue({
    el: '#app',
    router,
    data: {
        postList: [],
        loginUser: null,
    },
    methods: {
        signOut() {
            fetch(`/auth/logout`)
                .then((res) => res.json())
                .then(() => {
                    alert('sign-out succeed.');
                    this.loginUser = null;
                    router.push('/');
                })
        }
    },
});