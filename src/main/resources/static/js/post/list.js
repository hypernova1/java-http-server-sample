const PostListPage = Vue.component('board-list', {
    template: `
            <section>
                <h1>Post List</h1>
                <table>
                    <tr>
                        <th style="width: 20px">no</th>
                        <th style="width: 300px; text-align: left; padding: 10px">title</th>
                        <th style="width: 80px">writer</th>
                        <th style="width: 120px">register date</th>
                    </tr>
                    <tr is="board-item" v-for="(post, index) in postList" :post="post" :key="index"></tr>
                </table>
                <div style="width: 800px; text-align: center">
                    <div v-if="totalPage > 0" style="margin-top: 10px">
                        <button @click="changePage(page)" v-for="page in totalPage" :key="page" :disabled="currentPage === page">
                            {{ page }}
                        </button>
                    </div>
                </div>
                <a @click="goWriterPage">write</a>
           </section>
    `,
    data() {
        return {
            postList: [],
            totalPage: 0,
            totalCount: 0,
            currentPage: 1,
        }
    },
    created() {
        this.fetchPosts(this.currentPage);
    },
    methods: {
        fetchPosts(page) {
            fetch(`/post?page=${page}`)
                .then((res) => res.json())
                .then((data) => {
                    this.postList = data.items;
                    this.totalPage = data.totalPage;
                    this.totalCount = data.totalCount;
                });
        },
        changePage(page) {
            this.currentPage = page;
            this.fetchPosts(page);
        },
        goWriterPage() {
            if (vm.loginUser === null) {
                alert('login please..');
                return;
            }
            router.push('/post/new');
        }
    },
    computed: {
        totalPageArray() {
            return Array.from({ length: this.totalPage }, (v, k) => k + 1);
        }
    }
});