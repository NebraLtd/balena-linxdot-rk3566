# About This Repository

{{sw.stack.data.introduction}}

## About .NET Core

[.NET Core](https://docs.microsoft.com/dotnet/core/) is a general purpose development platform maintained by Microsoft and the .NET community on [GitHub](https://github.com/dotnet/core). It is cross-platform, supporting Windows, macOS and Linux, and can be used in device, cloud, and embedded/IoT scenarios.

.NET has several capabilities that make development easier, including a utomatic memory management, (runtime) generic types, reflection, asynchrony, concurrency, and native interop. Millions of developers take advantage of these capabilities to efficiently build high-quality applications.

You can use C# to write .NET Core apps. C# is simple, powerful, type-safe, and object-oriented while retaining the expressiveness and elegance of C-style languages. Anyone familiar with C and similar languages will find it straightforward to write in C#.

[.NET Core](https://github.com/dotnet/core) is open source (MIT and Apache 2 licenses) and was contributed to the [.NET Foundation](http://dotnetfoundation.org) by Microsoft in 2014. It can be freely adopted by individuals and companies, including for personal, academic or commercial purposes. Multiple companies use .NET Core as part of apps, tools, new platforms and hosting services.

You are invited to [contribute new features](https://github.com/dotnet/core/blob/master/CONTRIBUTING.md), fixes, or updates, large or small; we are always thrilled to receive pull requests, and do our best to process them as fast as we can.

> https://docs.microsoft.com/dotnet/core/

Watch [dotnet/announcements](https://github.com/dotnet/announcements/labels/Docker) for Docker-related .NET announcements.

{{#hw.device-type}}
{{import partial="dockerhub" combination="sw.stack+hw.device-type+balenalib"}}
{{/hw.device-type}}
{{#arch.sw}}
{{import partial="dockerhub" combination="sw.stack+arch.sw+balenalib"}}
{{/arch.sw}}
