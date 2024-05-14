Vue.component('board-item', {
    template: `
            <tr>
                <td style="width: 20px; text-align: center">{{ post.id }}</td>
                <td style="min-width: 500px; max-width: 500px; padding: 10px; cursor: pointer; white-space: nowrap; text-overflow: ellipsis; overflow: hidden">
                    <a @click="viewDetail(post.id)">{{ post.title }}</a>
                </td>
                <td style="width: 80px; text-align: center">{{ post.writer }}</td>
                <td style="width: 120px; text-align: center">{{ post.createdAt }}</td>
            </tr>
    `,
    props: ['post'],
    methods: {
        viewDetail(id) {
            router.push({ path: 'post/' + id });
        }
    }
});