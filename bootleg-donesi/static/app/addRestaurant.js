Vue.component("addRestaurant",{


    data: function(){
        return{
            zoom: 15,
            center: [20.395587077688546, 45.38230342193068],
            location: [20.395587077688546, 45.38230342193068],
            rotation: 0,
            restaurant:{
                name:"",
                type:"",
                location:{
                    geoLongitute:0,
                    geoLatitude:0,
                    adress:{
                        street:"",
                        town:"",
                        postalCode:"",
                        country:""
                    }
                },
                logoPath:"",
                username:"",
            },
            menagers:"",
            user:{
                firstName:"",
                lastName:"",
                username:"",
                password:"",
                gender:"",
                dateOfBirth:"",
                role:"MENAGER"
            }
        }
    },
    mounted(){
        axios
        .get('/availableMenagers')
        .then(response=>{
            this.menagers = response.data
        })
    },
    template:`
    	<div>
        	<h1>New restaurant registration form</h1>
            <div class="container">
                <form id="registrationFormRest" method ="POST" @submit.prevent = "registerRes">

                    <div>
                        <label for="name"><b>Name</b></label>
                        <input type="text" v-model="restaurant.name" placeholder = "Name" required/>
                    </div>

                    <div>
                        <label for="type"><b>Type</b></label>
                        <select name="type" v-model="restaurant.type" id="type" required>
                            <option value="ITALIAN">Italian</option>
                            <option value="CHINESE">Chinese</option>
                            <option value="GRILL">Grill</option>
                            <option value="MEXICAN">Mexican</option>
                            <option value="GREEK">Greek</option>
                        </select>
                    </div>
                    <div>
                    <label for="country"><b>Country</b></label>
                    <input type="text" v-model="restaurant.location.adress.country" placeholder = "Country" required/>
                    </div>
                    <div>
                    <label for="town"><b>Town</b></label>
                    <input type="text" v-model="restaurant.location.adress.town" placeholder = "Town" required/>
                    </div>
                    <div>
                    <label for="street"><b>Street</b></label>
                    <input type="text" v-model="restaurant.location.adress.street" placeholder = "Street" required/>
                    </div>
                    <div>
                    <label for="postalCode"><b>Postal Code</b></label>
                    <input type="text" v-model="restaurant.location.adress.postalCode" placeholder = "Postal Code" required/>
                    </div>
                    <div>
                    <label for="menager"><b>Menager</b></label>
                    <input type="text" v-model="restaurant.username" placeholder = "Menager" disabled/>
                    </div>
                    <div>
                    <label><b>Location</b></label>
                        <div class="map">
                            <vl-map data-projection="EPSG:4326" @click="changeLocation($event.coordinate)" style="height: 500px; width: 500px">
                            <vl-view :zoom.sync="zoom" :center.sync="center" :rotation.sync="rotation"></vl-view>
                                <vl-layer-tile>
                                <vl-source-osm></vl-source-osm>
                                </vl-layer-tile>
                                        <vl-feature>
                                <vl-geom-point :coordinates="location">
                                </vl-geom-point>
                                <vl-style-box>
                                    <vl-style-icon
                                        src="/marker/marker.png"
                                        :scale="0.05"
                                        :anchor="[0.5, 1]"
                                    ></vl-style-icon>
                                </vl-style-box>
                                </vl-feature>
                            </vl-map>
                        </div>
                    </div>
                    <p></p>
                    <div>
                        <button type = "submit"> Register </button>
                    </div>

                </form>
            </div>
            <div v-if="menagers">
        	<h1>Pick a menager for the restaurant</h1>
            <table style="width:99.999%">
                <thead>
                    <th style="width:30%">Name</th>
                    <th style="width:30%">Username</th>
                    <th style="width:30%">Role</th>
                </thead>
                <tbody>
                <tr v-for="u in menagers" @click="addMenager(u)" style="height:40px">
                   <td style="width:30%">{{u.firstName}} {{u.lastName}}</td>
                   <td style="width:30%">{{u.username}}</td>
                   <td style="width:30%">{{u.role}}</td>
                </tr>
               </tbody>
            </table>
            </div>
            <div v-if="!menagers">
        	<h1>Register a manager for the restaurant</h1>
            <div class="container">
                <form id="registrationForm" method ="POST" @submit.prevent = "registerMen">
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
        </div>
    `,
    methods:{
        registerRes(){
            if(this.restaurant.username){
                this.restaurant.location.geoLatitude = this.location[0];
                this.restaurant.location.geoLongitute = this.location[1];
                axios
                .post('/addRestaurant',this.restaurant)
                .then(response=>{
                    this.restaurant.name = ""
                    this.restaurant.type = "",
                    this.restaurant.status = "",
                    this.restaurant.location.geoLongitute = 0
                    this.restaurant.location.geoLatitude = 0
                    this.restaurant.location.adress.street = ""
                    this.restaurant.location.adress.town = ""
                    this.restaurant.location.adress.postalCode = ""
                    this.restaurant.location.adress.country = ""
                    this.restaurant.logoPath = ""
                    this.restaurant.username = ""
                    axios
                    .get('/availableMenagers')
                    .then(response=>{
                        this.menagers = response.data
                    })
                })
            }
            else{
                alert("A restaurant must have a manager");
            }
        },
        changeLocation(evt) {
            this.location = evt;
            
        },
        registerMen(){
            axios
            .post('/registerEmployee',this.user)
            .then(response=>{
                alert("Successfully registered a new menager");
                axios
                .get('/availableMenagers')
                .then(response=>{
                    this.menagers = response.data
                })
                
            })
            .catch((error) => {
                console.log("Error");
                alert("A user exists with the same username");
              });
        },
        addMenager(menager){
            this.restaurant.username = menager.username
        },

    }

})