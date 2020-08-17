const BoardList = Vue.component('board-list', {
    template: `
            <section>
                <h1>board-list</h1>
                <table>
                    <tr>
                        <th>no</th>
                        <th>title</th>
                        <th>writer</th>
                        <th>register date</th>
                    </tr>
                    <tr is="board-item" v-for="(post, index) in postList" :post="post" :key="index"></tr>
                </table>
                <router-link to="/board/new">write</router-link>
           </section>`,
    created() {
        fetch('/board')
            .then((res) => res.json())
            .then((data) => this.postList = data);
    },
    data() {
        return {
            postList: [],
        }
    },
});