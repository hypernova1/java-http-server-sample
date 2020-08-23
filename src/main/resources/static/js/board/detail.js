const BoardDetail = Vue.component('board-detail', {
    template:
        `
        <div>
            <h1>{{ postDetail.title }}</h1>
            <section>
                {{ postDetail.content }}
            </section>
            <div><button @click="goBack">list</button></div>
        </div>
    `,
    data() {
        return {
            postDetail: {},
        }
    },
    created() {
        const postId = this.$route.params.id;
        fetch(`/post/${postId}`)
            .then((res) => res.json())
            .then((data) => this.postDetail = data);
    },
    methods: {
        goBack() {
            router.go(-1);
        }
    }
});