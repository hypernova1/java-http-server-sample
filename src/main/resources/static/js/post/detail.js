const PostDetailPage = Vue.component('board-detail', {
    template:
        `
        <div>
            <h1>{{ postDetail.title }}</h1>
            <section v-html="formattedContent"></section>
            <div style="margin-top: 20px">
                <button @click="goListPage">list</button>
            </div>
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
        goListPage() {
            router.push('/post');
        }
    },
    computed: {
        formattedContent() {
            return this.postDetail.content ? this.postDetail.content.replace(/\n/g, '<br>') : '';
        }
    }
});