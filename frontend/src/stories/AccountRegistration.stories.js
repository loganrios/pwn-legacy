import React from "react";
import AccountRegistration from "./AccountRegistration";

export default {
  title: "AccountRegistration",
  component: AccountRegistration,
  argTypes: {
    onRegisterSubmit: {
      action: "Welcome to the Netherlands, we have cookies!",
    },
    onLoginNav: { action: "Go read a book. Or a Web Novel. Or something." },
  },
};

const Template = (args) => <AccountRegistration {...args} />;

export const Primary = Template.bind({});
Primary.args = {};
