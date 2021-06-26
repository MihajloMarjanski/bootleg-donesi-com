Vue.component("allUsers",{

    data: function(){
        return{
            users:""
        }
    },
    mounted(){
        axios
        .get('/allUsers')
        .then(response=>{
            this.users = response.data
            console.log(response.data)
        })
    },
    template:`
    	<div>
        	<h1>User list</h1>
            <div v-for="u in users">
                <div style="border:1px solid black">
                    <p>Name: {{u.firstName}} {{u.lastName}} | Username: {{u.username}} | Role: {{u.role}}</p>
                </div> 
            </div>
            <div>
                <button type= "button" v-on:click="cancel">Cancel</button>
            </div>
        </div>
    `,
    methods:{
        cancel(){
            this.$router.push("/")
        }
    }

})