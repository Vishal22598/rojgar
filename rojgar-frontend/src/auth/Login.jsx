import React, { useContext, useState } from 'react'
import { AuthContext } from './AuthContext'
import api from '../api/axiosConfig';

function Login() {
    const {login} = useContext(AuthContext);
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = async () => {
        const res = await api.post("/auth/login", {
            email, 
            password
        });

        login(res.data.token);
    };

  return (
    <div>
      <h2>Login</h2>
      <input onChange={(e) => setEmail(e.target.value)} /><br/>
      <input type="password" onChange={(e) => setPassword(e.target.value)} /><br/>
      <button onClick={handleLogin}>Login</button>
    </div>
  )
}

export default Login;