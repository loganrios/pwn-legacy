import React from "react";
import { Button, Typography, Box, TextField } from "@mui/material";

const Login = ({ onLoginSubmit, onRegisterNav }) => {
  const [email, setEmail] = React.useState("");
  const [password, setPassword] = React.useState("");

  return (
    <Box sx={{ display: "flex", flexDirection: "column", gap: 1 }}>
      <TextField
        id="login email"
        label="Email"
        value={email}
        onChange={(evt) => setEmail(evt.target.value)}
      />
      <TextField
        id="login password"
        label="Password"
        type="password"
        value={password}
        onChange={(evt) => setPassword(evt.target.value)}
      />
      <Button
        variant="contained"
        onClick={() => onLoginSubmit(email, password)}
      >
        Login
      </Button>
      <Button variant="outlined" onClick={onRegisterNav}>
        Don't have an account?
      </Button>
    </Box>
  );
};
export default Login;
