const PostListPage = Vue.component('board-list', {
    template: `
            <section class="post-list">
                <h1>Post</h1>
                <div class="table-wrapper">
                  <table>
                    <thead>
                      <tr>
                        <th>no</th>
                        <th>Title</th>
                        <th>Writer</th>
                        <th>Register Date</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr is="board-item" v-for="(post, index) in postList" :post="post" :key="index"></tr>
                    </tbody>
                  </table>
                </div>
                <div class="pagination">
                  <div v-if="totalPage > 0" class="pagination-buttons">
                    <button @click="changePage(page)" v-for="page in totalPageArray" :key="page" :disabled="currentPage === page">{{ page }}</button>
                  </div>
                </div>
                <a @click="goWriterPage" class="write-button">Write</a>
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