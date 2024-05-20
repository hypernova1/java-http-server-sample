const routes = [
    { path: '/', redirect: '/post' },
    { path: '/post', component: PostListPage, props: true },
    { path: '/post/new', component: PostInsertPage },
    { path: '/post/:id', component: PostDetailPage },
    { path: '/sign-up', component: SignUpPage },
    { path: '/sign-in', component: SignInPage },
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
                .then(() => {
                    alert('sign-out succeed.');
                    this.loginUser = null;
                })
                .catch((error) => {
                    console.error('Error during sign-out:', error);
                });
        }
    },
});