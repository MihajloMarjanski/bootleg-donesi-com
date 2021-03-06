Vue.component("registerEmployee",{

    data: function(){
        return{
            user:{
                firstName:"",
                lastName:"",
                username:"",
                password:"",
                gender:"",
                dateOfBirth:"",
                role:"",
            }
        }
    },
    mounted(){

    },
    template:`
    	<div>
        	<h1>Employee registration form</h1>
            <div class="container">
                <form id="registrationForm" method ="POST" @submit.prevent = "register">
                    <div>
                        <label for="firstName"><b>First Name</b></label>
                        <input type="text" v-model="user.firstName" placeholder = "First Name" required/>
                    </div>
                    <div>
                        <label for="lastName"><b>Last Name</b></label>
                        <input type="text" v-model="user.lastName" placeholder = "Last Name" required/>
                    </div>
                    <div>
                        <label for="username"><b>Username</b></label>
                        <input type="text" v-model="user.username" placeholder = "Username" required/>
                    </div>
                    <div>
                        <label for="password"><b>Password</b></label>
                        <input type="password" v-model="user.password" placeholder = "Password" required/>
                    </div>
                    <div>
                        <label for="gender"><b>Gender</b></label>
                        <select name="gender" v-model="user.gender" id="gender" required>
                            <option value="MALE">Male</option>
                            <option value="FEMALE">Female</option>
                        </select>
                    </div>
                    <div>
                    <label for="role"><b>Role</b></label>
                    <select name="role" v-model="user.role" id="role" required>
                        <option value="MENAGER">Menager</option>
                        <option value="COURIER">Courier</option>
                    </select>
                </div>
                    <div>
                        <label for="date"><b>Date of birth</b></label>
                        <input type="date" v-model="user.dateOfBirth" required/>
                    </div>
                    <p></p>
                    <div>
                        <button type = "submit"> Register</button>
                    </div>
                </form>
            </div>
        </div>
    `,
    methods:{
        register(){
        	console.log(this.user)
            axios
            .post('/registerEmployee',this.user)
            .then(response=>{
                alert("Successfully registered a new employee");
                    this.user.firstName = ""
                    this.user.lastName = ""
                    this.user.username = ""
                    this.user.password =""
                    this.user.gender = ""
                    this.user.dateOfBirth = ""
                    this.user.role = ""
                
            })
            .catch((error) => {
                console.log("Error");
                alert("A user exists with the same username");
              });
        },

    }

})