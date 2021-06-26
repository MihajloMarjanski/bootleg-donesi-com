Vue.component("login",{

    data: function(){
        return{
            user:{
                username:"",
                password:"",
            }
        }
    },
    mounted(){

    },
    template:`
    	<div>
        	<h1>Login form</h1>
            <form id="loginForm" method ="POST" @submit.prevent = "login">
                <div>
                    <label for="username"><b>Username</b></label>
                    <input type="text" v-model="user.username" placeholder = "Username" required/>
                </div>
                <div>
                    <label for="password"><b>Password</b></label>
                    <input type="password" v-model="user.password" placeholder = "Password" required/>
                </div>
                <div>
                    <button type = "submit">Login</button>
                    <button type= "button" v-on:click="cancel">Cancel</button>
                </div>
            </form>
        </div>
    `,
    methods:{
        login(){
            axios
            .post('/loginUser',this.user)
            .then(response=>{
                localStorage.setItem('user',response.data)
                console.log(response.data)
                this.$router.push('/')
            })
            .catch((error) => {
                console.log("Error");
                alert("Invalid username or password");
              });
        },

        cancel(){
            this.$router.push("/")
        }
    }

})