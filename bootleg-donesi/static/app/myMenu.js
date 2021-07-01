Vue.component("myMenu",{

    data: function(){
        return{
            getFor:{
                username: "",
            },
            menu:"",
            menuitem:""
        }
    },
    mounted(){
        this.getFor.username = localStorage.getItem('username')
        axios
        .post('/getMenu',this.getFor)
        .then(response=>{
            this.menu = response.data
        })
    },
    template:`
    	<div>
        <h1>Menu</h1>
        <div>
            <table style="width:99.999%">
                <thead>
                    <th style="width:20%">Picture</th>
                    <th style="width:15%">Name</th>
                    <th style="width:40%">Description</th>
                    <th style="width:5%">Price</th>
                </thead>
                <tbody>
                    <tr v-for="i in menu" @click="view(i)">
                        <td style="width:20%"> <img :src="i.picturePath" width="75" height="75" ></td>
                        <td style="width:15%">{{i.name}}</td>
                        <td style="width:40%">{{i.description}}</td>
                        <td style="width:5%">{{i.price}}</td>
                    </tr>                                                                  
                </tbody>
            </table>            
        </div>
        <div class="container" v-if="menuitem">
            <form id="registrationForm" method ="POST" @submit.prevent = "changeItem">
                <div>
                    <label for="name"><b>Name*</b></label>
                    <input type="text" v-model="menuitem.name" placeholder = "Name" required/>
                </div>
                <div>
                    <label for="description"><b>Description</b></label>
                    <input type="text" v-model="menuitem.description" placeholder = "Description"/>
                </div>
                <div>
                    <label for="type"><b>Type*</b></label>
                    <select name="type" v-model="menuitem.type" id="type" required>
                        <option value="FOOD">Food</option>
                        <option value="DRINK">Drink</option>
                    </select>
                </div>
                <div>
                    <label for="quantity"><b>Quantity</b></label>
                    <input type="number" v-model="menuitem.quantity" placeholder = "Quantity"/>
                </div>
                <div>
                    <label for="type"><b>Quantity Type</b></label>
                    <select name="quantityType" v-model="menuitem.quantityType" id="quantityType">
                        <option value="GRAMS">Grams</option>
                        <option value="MILLILITERS">Milliliters</option>
                    </select>
                </div>
                <div>
                    <label for="price"><b>Price*</b></label>
                    <input type="number" v-model="menuitem.price" placeholder = "Price" required/>
                </div>
                <p></p>
                <div>
                    <button type = "submit"> Change</button>
                </div>
            </form>
        </div>
        </div>

    `,
    methods:{
        view(item){
            this.menuitem = JSON.parse(JSON.stringify(item))
        },

        changeItem(){
            axios
            .post('/changeItem',this.menuitem)
            .then(response=>{
                this.menuitem = ""
                axios
                .post('/getMenu',this.getFor)
                .then(response=>{
                    this.menu = response.data
                })
            })
            .catch((error) => {
                console.log("Error");
                alert("A menu item with the same name already exists in your restaurant");
              });
        }

    }

})