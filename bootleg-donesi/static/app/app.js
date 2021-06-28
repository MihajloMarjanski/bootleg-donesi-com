const Home = { template: '<home></home>' }
const Register = { template: '<register></register>' }
const Login = { template: '<login></login>' }
const RegisterEmployee = { template: '<registerEmployee></registerEmployee>' }
const AllUsers = { template: '<allUsers></allUsers>' }
const AllRestaurants = { template: '<allRestaurants></allRestaurants>' }
const MyAccount = { template: '<myAccount></myAccount>' }
const AddRestaurant = { template: '<addRestaurant></addRestaurant>' }

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'home', component: Home},
		{ path: '/register', name: 'register', component: Register},
		{ path: '/login', name: 'login', component: Login},
		{ path: '/registerEmployee', name: 'registerEmployee', component: RegisterEmployee},
		{ path: '/allUsers', name: 'allUsers', component: AllUsers},
		{ path: '/allRestaurants', name: 'allRestaurants', component: AllRestaurants},
		{ path: '/myAccount', name: 'myAccount', component: MyAccount},
		{ path: '/addRestaurant', name: 'addRestaurant', component: AddRestaurant}
	  ]
});

var app = new Vue({
	router,
	el: '#app'
});