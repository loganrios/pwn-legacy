import React from "react";
import Login from "./Login";

export default {
  title: "Login",
  component: Login,
  argTypes: {
    onLoginSubmit: { action: "Being logged in means you're cool" },
    onRegisterNav: { action: "Join us! Join us! Join us!" },
  },
};

const Template = (args) => <Login {...args} />;

export const Primary = Template.bind({});
Primary.args = {};
