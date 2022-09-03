import React from "react";
import WebLinksList from "./WebLinksList";

export default {
  title: "WebLinksList",
  component: WebLinksList,
  argTypes: {
    onEdit: { action: "Change is inevitable" },
    onSubmit: { action: "You have saved your changes." },
  },
};

const Template = (args) => <WebLinksList {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  readingList: "www.projectwebnovel.com/kjforthman/reading-list",
  links: [
    {
      label: "Personal Site",
      url: "https://www.kjforthman.com"
    },
    {
      label: "Discord",
      url: ""
    },
    {
      label: "Facebook",
      url: "https://www.facebook.com/kjforthman/"
    },
  ],
};
