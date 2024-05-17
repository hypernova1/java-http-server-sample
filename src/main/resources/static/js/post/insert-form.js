const PostInsertPage = Vue.component('board-insert-form', {
    template: `
            <div>
                <h1>insert form</h1>
                <div>
                    <label for="title">title: 
                        <input type="text" name="title" v-model="form.title">
                    </label>
                </div>
                <div>
                    <label for="content">content: 
                        <textarea name="content" v-model="form.content"></textarea>
                    </label>
                </div>
                <button @click="registerPost">submit</button>
            </div>
    `,
    data() {
        return {
            form: {
                title: '',
                content: '',
            }
        }
    },
    methods: {
        registerPost() {
            if (this.form.title.length === 0) {
                alert('title cannot be empty.');
                return;
            }
            if (this.form.content.length === 0) {
                alert('content cannot be empty.');
                return;
            }
            fetch('/post', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(this.form),
            }).then((res) => res.json())
                .then((data) => {
                    if (data.status !== 201) {
                        alert(err);
                        return;
                    }
                    router.push(`/post/${data}`);
                }).catch((err) => alert(err));
        }
    }
});