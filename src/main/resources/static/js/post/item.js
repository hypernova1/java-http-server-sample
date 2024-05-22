Vue.component('board-item', {
    template: `
            <tr>
                <td class="post-id">{{ post.id }}</td>
                <td class="post-title" @click="viewDetail(post.id)"><a>{{ post.title }}</a></td>
                <td class="post-writer">{{ post.writer }}</td>
                <td class="post-date">{{ post.createdAt }}</td>
            </tr>
    `,
    props: ['post'],
    methods: {
        viewDetail(id) {
            router.push({ path: 'post/' + id });
        }
    }
});