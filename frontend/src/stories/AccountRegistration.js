import React from "react";
import { Button, Box, TextField } from "@mui/material";

const Login = ({ onRegisterSubmit, onLoginNav }) => {
  const [email, setEmail] = React.useState("");
  const [password, setPassword] = React.useState("");
  const [username, setUsername] = React.useState("");

  return (
    <Box sx={{ display: "flex", flexDirection: "column", gap: 1 }}>
      <TextField
        id="username"
        label="Display Name"
        value={username}
        onChange={(evt) => setUsername(evt.target.value)}
      />
      <TextField
        id="email"
        label="Email"
        value={email}
        onChange={(evt) => setEmail(evt.target.value)}
      />
      <TextField
        id="password"
        label="Password"
        type="password"
        value={password}
        onChange={(evt) => setPassword(evt.target.value)}
      />
      <Button
        variant="contained"
        onClick={() => onRegisterSubmit(username, email, password)}
      >
        Create Account
      </Button>
      <Button variant="outlined" onClick={onLoginNav}>
        Already have an account?
      </Button>
    </Box>
  );
};
export default Login;
